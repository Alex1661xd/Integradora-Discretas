package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;

public class Task implements Comparable<Task> {

    private String title;
    private String identifier;
    private String description;
    private String date;
    private TypePriority TipoPrioridad;

    
    public void setDate(String date) {
        this.date = date;
    }

    public TypePriority getTipoPrioridad() {
        return TipoPrioridad;
    }

    public void setTipoPrioridad(TypePriority tipoPrioridad) {
        TipoPrioridad = tipoPrioridad;
    }

    public Task(String title, String description, String date, TypePriority TipoPrioridad, String identifier) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.TipoPrioridad= TipoPrioridad;
        this.identifier=identifier;
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

    /*public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }*/

    public String fechaString(Calendar fecha){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(fecha);
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

    

}
