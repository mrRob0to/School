//Robert Antenorcruz
#include <stdio.h>
#include <sys/types.h>
#include <fcntl.h>
#include <unistd.h>
#include <stdlib.h>
#include <sys/shm.h>
#include <sys/mman.h>
#include <sys/types.h>
#include <sys/wait.h>

//Function declaration converting string to int
int stringToInt(const char *s);

//Global variables for shared memory in and out counter
int in = 0, out = 0;

int main(int argc, const char *argv[]) {

    //Initializing file descriptors
    int fd, f1, f2;
    int fd2[2];

    //Initializing user arguments
    const char *CHUNK_SIZE = argv[1];
    const char *BUFFER_SIZE = argv[2];

    //Converting user string arguments into integers
    int BSIZE = stringToInt(BUFFER_SIZE);
    int CSIZE = stringToInt(CHUNK_SIZE);

    //Initializing pipe and .out buffers
    char readFromPipe[64];
    char sendToPipe[64];
    char childBuffer[64];
    char parentBuffer[64];

    //Using fseek() to find the number of bytes in the input.txt
    FILE *fp;
    fp = fopen(argv[3], "r");
    fseek(fp, 0, SEEK_END);
    int endPOS = ftell(fp);
    int startPOS = 0;

    //Creating the .out file
    int outFile = open("Antenorcruz-23014206.txt", O_RDWR | O_APPEND | O_CREAT, S_IRUSR | S_IWUSR);

    //Handling user arguments
    if (argc != 5) {
        write(STDERR_FILENO, "Wrong number of command line arguments\n", 128);
        //printf("Wrong number of command line arguments\n");
        exit(1);
    }

    if ((f1 = open(argv[3], O_RDONLY, 0)) == 1) {
        write(STDERR_FILENO, "Can't open input file\n", 128);
        return 3;
    }

    if ((f2 = creat(argv[4], 0644)) == -1) {
        write(STDERR_FILENO, "Can't create output file\n", 128);
        return 4;
    }

    //POSIX SHARED_MEMORY
    char *ptr;
    int shMemParentCharCount = 0, shMemChildCharCount = 0;

    const char *name = "SHARED_MEM";
    fd = shm_open(name, O_CREAT | O_RDWR, 0666);
    ftruncate(fd, BSIZE);
    ptr = (char *) mmap(0, BSIZE, PROT_READ | PROT_WRITE, MAP_SHARED, fd, 0);

    //Creating pipe
    if (pipe(fd2) < 0) {
        write(STDERR_FILENO, "Pipe failed\n", 128);
        return 1;
    }


    pid_t pid;
    //printf("CREATING FORK\n");
    pid = fork();

    // If creating Process failed
    if (pid < 0) {
        write(STDERR_FILENO, "Fork failed\n", 128);

    }

    /*Child Process of consumer - reads from the shared buffer and writes to .txt,
    Increments shared memory read pointer by CHUNK SIZE to next address to read in shared memory.
    Increments counter variables (startPOS, shMemChildCharCount)*/
    else if (pid == 0) {
        while (startPOS < endPOS) {
            (write(f2, ptr, CSIZE) != CSIZE);
            out = (out + 1) % BSIZE;
            ptr += CSIZE;
            startPOS += CSIZE;
            shMemChildCharCount += CSIZE;
            //printf("CHILD OUT: %d \n", out);
        }

        //Opening pipe from parent process
        close(fd2[1]);
        read(fd2[0], readFromPipe, 64);

        //Write to screen
        sprintf(childBuffer, "CONSUMER: The consumer value of shMemChildCharCount = %d\n", shMemChildCharCount);
        write(STDOUT_FILENO, readFromPipe, 64);
        write(STDOUT_FILENO, childBuffer, 64);

        //Write to .out
        write(outFile, readFromPipe, 64);
        write(outFile, childBuffer, 64);
    }

    /*Parent Process - 1st while loop checks if shared memory is full
    2nd while loop reads from the input file and writes it into the shared buffer
    Increments shared memory pointer ptr by CHUNK SIZE to next address in shared memory*/
    else if (pid > 0) {
        while (((in + 1) % BSIZE) == out) { ;
        }
        while (read(f1, ptr, CSIZE) > 0) {
            in = (in + 1) % BSIZE;
            ptr += CSIZE;
            shMemParentCharCount += CSIZE;
            //printf("PARENT IN: %d \n", in);
        }

        //Packing string into char array to send into pipe
        sprintf(sendToPipe, "CONSUMER: The producer value of shMemParentCharCount = %d\n", shMemParentCharCount);
        sprintf(parentBuffer, "PRODUCER: The producer value of shMemParentCharCount = %d\n", shMemParentCharCount);

        //Writing string output into .out file
        write(outFile, parentBuffer, 64);

        //Using pipe to send char[] of shMemParentCharCount
        close(fd2[0]);
        write(fd2[1], sendToPipe, 64);
        write(STDOUT_FILENO, parentBuffer, 64);
    }

    //Close pipes, FD, .txt files, and unlink shared memory
    close(fd2[0]);
    close(fd2[1]);
    close(f1);
    close(f2);
    close(outFile);
    shm_unlink(ptr);
    return 0;
}

//Function to convert the arguments (string) of CHUNK_SIZE and BUFFER_SIZE into int
int stringToInt(const char *s) {
    int num = 0;
    while (*s) {
        num = ((*s) - '0') + num * 10;
        s++;
    }
    return num;
}