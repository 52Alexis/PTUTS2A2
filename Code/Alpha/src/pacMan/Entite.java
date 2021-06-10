package pacMan;

import java.util.Objects;

/**
 * Classe abstraite permettant de gérer les fixes et les mobile et de le comparer
 */
public abstract class Entite {
    protected Case emplacement;

    public Entite(Case emplacement){
        this.emplacement=emplacement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Entite entite = (Entite) o;
        return Objects.equals(emplacement, entite.emplacement);
    }


}
