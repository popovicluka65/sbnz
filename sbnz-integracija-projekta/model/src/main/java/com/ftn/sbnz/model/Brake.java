package com.ftn.sbnz.model;

import com.ftn.sbnz.model.enums.BrakeState;

// @Entity
// @Table(name = "brake")
// @Data
// @AllArgsConstructor
public class Brake {
    private Integer pedalPressure;   // pritisak pedale
    private Integer brakeFluid;      // nivo kočione tečnosti
    private Integer padWear;         // istrošenost kočionih pločica
    private BrakeState state;        // trenutno stanje sistema

    public Brake() {
        this.state = BrakeState.NORMAL;
        this.pedalPressure = 0;
        this.brakeFluid = 100;
        this.padWear = 0;
    }

    public Integer getPedalPressure() { return pedalPressure; }
    public void setPedalPressure(Integer pedalPressure) { this.pedalPressure = pedalPressure; }

    public Integer getBrakeFluid() { return brakeFluid; }
    public void setBrakeFluid(Integer brakeFluid) { this.brakeFluid = brakeFluid; }

    public Integer getPadWear() { return padWear; }
    public void setPadWear(Integer padWear) { this.padWear = padWear; }

    public BrakeState getState() { return state; }
    public void setState(BrakeState state) { this.state = state; }
}