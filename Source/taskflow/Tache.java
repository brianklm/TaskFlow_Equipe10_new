package Source.taskflow;
import java.time.LocalDate;

public class Tache {
    private final int id;
    private final String titre;
    private final Priorite priorite;
    private final LocalDate echeance;
    private boolean terminee;

    public Tache(int id, String titre, Priorite priorite, LocalDate echeance) {
        this.id = id;
        this.titre = titre;
        this.priorite = priorite;
        this.echeance = echeance;
        this.terminee = false;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public Priorite getPriorite() {
        return priorite;
    }

    public LocalDate getEcheance() {
        return echeance;
    }

    public boolean estTerminee() {
        return terminee;
    }

    public void terminer() {
        terminee = true;
    }
}
