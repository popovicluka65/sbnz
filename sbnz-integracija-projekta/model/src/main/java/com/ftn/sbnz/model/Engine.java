package com.ftn.sbnz.model;


import com.ftn.sbnz.model.enums.State;
// import lombok.AllArgsConstructor;
// import lombok.Data;
// import javax.persistence.Column;
// import javax.persistence.Entity;
// import javax.persistence.Id;
// import javax.persistence.Table;

// @Entity
// @Table(name = "engine")
// @Data
// @AllArgsConstructor
public class Engine {
    //@Id
    //private Long id;
    //@Column
    private double temperature;
    //@Column
    private double coolantLevel;
    //@Column
    private boolean fanActive;
    //@Column
    private boolean pressureDrop;
    //@Column
    private State state;

    public Engine() {
    }

//    public Long getId() {
//        return id;
//    }

//    public void setId(Long id) {
//        this.id = id;
//    }

   public double getTemperature() {
       return temperature;
   }

   public void setTemperature(double temperature) {
       this.temperature = temperature;
   }

   public double getCoolantLevel() {
       return coolantLevel;
   }

   public void setCoolantLevel(double coolantLevel) {
       this.coolantLevel = coolantLevel;
   }

   public boolean isFanActive() {
       return fanActive;
   }

   public void setFanActive(boolean fanActive) {
       this.fanActive = fanActive;
   }

   public boolean isPressureDrop() {
       return pressureDrop;
   }

   public void setPressureDrop(boolean pressureDrop) {
       this.pressureDrop = pressureDrop;
   }

   public State getState() {
       return state;
   }

   public void setState(State state) {
       this.state = state;
   }
}
