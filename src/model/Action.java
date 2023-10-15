package model;

public class Action {
    TypeAction typeAction;
    String actionR;
    String atributef;
    String dataBefore;
    String dataAfter;
    int posicionEditAtributo;
    String id;
    Task tareaEliminate;

    
    public Action(TypeAction typeAction, String actionR, String atributef, String dataBefore, String dataAfter,
            int posicionEditAtributo, String id) {
        this.typeAction = typeAction;
        this.actionR = actionR;
        this.atributef = atributef;
        this.dataBefore = dataBefore;
        this.dataAfter = dataAfter;
        this.posicionEditAtributo=posicionEditAtributo;
        this.id=id;
    }
    public Task getTareaEliminate() {
        return tareaEliminate;
    }
    public void setTareaEliminate(Task tareaEliminate) {
        this.tareaEliminate = tareaEliminate;
    }
    public int getPosicionEditAtributo() {
        return posicionEditAtributo;
    }
    public void setPosicionEditAtributo(int posicionEditAtributo) {
        this.posicionEditAtributo = posicionEditAtributo;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public TypeAction getTypeAction() {
        return typeAction;
    }
    public void setTypeAction(TypeAction typeAction) {
        this.typeAction = typeAction;
    }
    public String getActionR() {
        return actionR;
    }
    public void setActionR(String actionR) {
        this.actionR = actionR;
    }
    public String getAtributef() {
        return atributef;
    }
    public void setAtributef(String atributef) {
        this.atributef = atributef;
    }
    public String getDataBefore() {
        return dataBefore;
    }
    public void setDataBefore(String dataBefore) {
        this.dataBefore = dataBefore;
    }
    public String getDataAfter() {
        return dataAfter;
    }
    public void setDataAfter(String dataAfter) {
        this.dataAfter = dataAfter;
    }
  
    public String ToStringEdit() {
        return "\nAction: " + typeAction + "\n" + actionR + "\nAtributo afectado: " + atributef + "\nAtributo antes: "+dataBefore+"\nAtributo despues: "+dataAfter;
    }
    
    public String ToStringN() {
        return "Action: " + typeAction + "\n" + actionR+"\nId: ["+id+"]";
    }
}
