package de.dickeLunten.coronaPacman.models.panel;

public class CreditsModel {
    private String creditslTXT;

    public CreditsModel(){
        creditslTXT = "<html><br /><br />Created by<br /><i>Yannick Wegel, Colin Clauß,<br />Jake Finch, Felix Rosner, Daniel Bund</i></html>";
    }

    public String getCreditslTXT() {
        return creditslTXT;
    }
}
