package taskflow;

public enum Priorite {
    Basse,
    Normale,
    Élevée;    
    public static Priorite depuisTexte(String texte) {
        if (texte == null || texte.trim().isEmpty()) {
            return Normale;
        }

        for (Priorite priorite : values()) {
            if (priorite.name().equalsIgnoreCase(texte.trim())) {
                return priorite;
            }
        }

        throw new IllegalArgumentException(
                "Priorité invalide : " + texte
        );
    }

}
