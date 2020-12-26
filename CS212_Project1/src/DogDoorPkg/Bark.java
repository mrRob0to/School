package DogDoorPkg;

public class Bark extends BarkRecognizer {

    private String voiceSample;

    //Constructors
    Bark(){
        voiceSample = null;
    }

    Bark(String voice){ this.voiceSample = voice; }

    Bark(Bark copy){ voiceSample = copy.voiceSample; }

    //Getters & Setters
    String getVoiceSample(){ return voiceSample; }
    void setVoiceSample(String n){ voiceSample = n; }


    @Override
    public String toString(){
        String str = "Bark object voice sample is: " + voiceSample;
        return str;
    }



}
