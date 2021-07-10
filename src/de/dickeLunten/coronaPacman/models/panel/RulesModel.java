package de.dickeLunten.coronaPacman.models.panel;

public class RulesModel {
    private String rulesL_DE;
    private String rulesL_EN;

    public RulesModel(){
        rulesL_DE = "<html><i>Regeln:</i><br />Oh nein! Die Welt geht in einer Stunde unter, <b>Jimbo</b> muss aber noch shoppen gehen.<br /><b>Jimbo</b> braucht noch sein Mountain Dew, Doritos und ein Waifu-Pillow (und Klopapier natürlich auch).<br />Böse Coronaviren wollen <b>Jimbo</b>s Vorhaben aber for some reason verhindern.<br />Hilf <b>Jimbo</b> dabei, mit Impfungen die Viren zu zerstören und <b>Jimbo</b>s local super market leerzuräumen!</html>";
        rulesL_EN = "<html><i>Rules:</i><br />Oh no! The end of the world is coming in one hour, but <b>Jimbo</b> still has to go shopping.<br /><b>Jimbo</b> still needs Mounain Dew, Doritos and a Waifu-Pillow (and of course some toilet paper as well).<br />Unfortunately, bad corona viruses want to prevent <b>Jimbo</b>'s plan for some reason.<br /> Help <b>Jimbo</b> to destroy the viruses with vaccinations and to empty his local supermarket!";
    }

    public String getRulesL_DE(){
        return rulesL_DE;
    }

    public String getRulesL_EN() {
        return rulesL_EN;
    }
}
