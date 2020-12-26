package DogDoorPkg;

import java.util.concurrent.TimeUnit;

public class BarkRecognizer extends DogDoor {

    private String voiceKey;

    //Constructors
    BarkRecognizer(){
        voiceKey = null;
    }

    BarkRecognizer(Bark a){
       voiceKey = a.getVoiceSample();
    }

    BarkRecognizer(BarkRecognizer copy){
        this.voiceKey = copy.voiceKey;
    }

    @Override
    public String toString(){
        String str = "BarkRecognizer voiceKey is: " + voiceKey;
        return str;
    }

    //Getters & Setters
    String getVoiceKey(){return voiceKey;}
    void setVoiceKey(String n){voiceKey = n;}


    //Methods
    void BarkRecognizerListen(Bark n) throws InterruptedException {
        TimeUnit time = TimeUnit.SECONDS;
        System.out.println("Listening for bark...");
        time.sleep(5);
        if(voiceKey.equals(n.getVoiceSample()) ){
            System.out.println("Voice Match successful!\nHello Dog");
            doorTimer();
        }
    }

}
