package com.d4viddf.Tablas;

public class Departamentos {
    public int id;
    public String nombre;
    public float presupuesto;
    public String desc;

    
    /** 
     * @return String
     */
    public String getDesc() {
        return desc;
    }

    
    /** 
     * @param desc
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    
    /** 
     * @return int
     */
    public int getId() {
        return this.id;
    }

    
    /** 
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    
    /** 
     * @return String
     */
    public String getNombre() {
        return this.nombre;
    }

    
    /** 
     * @param nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    /** 
     * @return float
     */
    public float getPresupuesto() {
        return this.presupuesto;
    }

    
    /** 
     * @param presupuesto
     */
    public void setPresupuesto(float presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Departamentos(int id, String nombre, float presupuesto, String desc) {
        this.id = id;
        this.nombre = nombre;
        this.presupuesto = presupuesto;
        this.desc = desc;
    }

    public Departamentos() {
    }

}
