package de.dickeLunten.coronaPacman.models.panel;

public class RulesModel {
    private String ruleslTXT;

    public RulesModel(){
        ruleslTXT = "<html><br /><i>Regeln:</i><br />Oh nein! Die Welt geht in einer Stunde unter, <b>Jimbo</b> muss aber noch shoppen gehen.<br /><b>Jimbo</b> braucht noch sein Mountain Dew, Doritos und ein Waifu-Pillow (und Klopapier natürlich auch).<br />Böse Coronaviren wollen <b>Jimbo</b>s Vorhaben aber for some reason verhindern.<br />Hilf <b>Jimbo</b> dabei, mit Impfungen die Viren zu zerstören und <b>Jimbo</b>s local super market leerzuräumen!</html>";
    }

    public String getRuleslTXT(){
        return ruleslTXT;
    }
}
