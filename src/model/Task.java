package model;
import java.time.LocalDate;


public class Task {
    
    private String title;
    private String identifier;
    private String description;
    private LocalDate date;
    private TypePriority TipoPrioridad;
    
    public Task(String title, String description, LocalDate date, TypePriority TipoPrioridad, String identifier) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.TipoPrioridad= TipoPrioridad;
        this.identifier=identifier;
    }
    
    public void setDate(LocalDate date) {
        this.date = date;
    }

    public TypePriority getTipoPrioridad() {
        return TipoPrioridad;
    }

    public void setTipoPrioridad(TypePriority tipoPrioridad) {
        TipoPrioridad = tipoPrioridad;
    }


    public LocalDate getDate() {
        return date;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public String toString() {
        return "\nIdentifier: "+identifier+"\nTitle: " + title + "\nDescription: " + description + "\nDate: " + date;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
    * Compare this date with another LocalDate.
    *
    * @param fechaComparar The date to compare with.
    * @return -1 if the current date is earlier, 0 if the dates are the same, 1 if the current date is later.
    */
    public int compareTo(LocalDate fechaComparar) {
        if (this.date.isBefore(fechaComparar)) {
            return -1; // current date is earlier
        } else if (this.date.equals(fechaComparar)) {
            return 0;  // the dates are the same
        } else {
            return 1;  // current date is later
        }
    }
<<<<<<< HEAD
    
    public int getTypeModification() {
        return typeModification;
    }

    public void setTypeModification(int typeModification) {
        this.typeModification = typeModification;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public void setValorAnterior(String valorAnterior) {
        this.valorAnterior = valorAnterior;
    }

    public int getPosicionEditAtributo() {
        return posicionEditAtributo;
    }

    public void setPosicionEditAtributo(int posicionEditAtributo) {
        this.posicionEditAtributo = posicionEditAtributo;
    }

    

}
=======
       

}
>>>>>>> 61ece653d5d44f0c8051972b3210fc246aebf4bd
