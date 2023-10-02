package model;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task {
    

    private String title;
    private String identifier;
    private String description;
    private String date;
    private TypePriority TipoPrioridad;
    private String descriptionUseRealized;
    private int typeModification;
    private String valorAnterior;
    private int posicionEditAtributo;
    
    public Task(String title, String description, String date, TypePriority TipoPrioridad, String identifier, String descriptionUseRealized) {
        this.title = title;
        this.description = description;
        this.date = date;
        this.TipoPrioridad= TipoPrioridad;
        this.identifier=identifier;
        this.descriptionUseRealized=descriptionUseRealized;
    }
    
    public void setDate(String date) {
        this.date = date;
    }

    public TypePriority getTipoPrioridad() {
        return TipoPrioridad;
    }

    public void setTipoPrioridad(TypePriority tipoPrioridad) {
        TipoPrioridad = tipoPrioridad;
    }


    public String getDate() {
        return date;
    }

    public String getDescriptionUseRealized() {
        return descriptionUseRealized;
    }

    public void setDescriptionUseRealized(String descriptionUseRealized) {
        this.descriptionUseRealized = descriptionUseRealized;
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

    public Date convertStringToDate(){
        SimpleDateFormat formato = new SimpleDateFormat("MM/dd/yyyy");
        Date fecha=null;
        try {
            fecha = formato.parse(this.date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return fecha;
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

    public int compareTo(Date fechaComparar){
        return convertStringToDate().compareTo(fechaComparar);
    }

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
