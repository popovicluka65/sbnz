package com.ftn.sbnz.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import com.ftn.sbnz.model.enums.BrakeState;
import com.ftn.sbnz.model.Brake;
import com.ftn.sbnz.model.utils.KnowledgeSessionHelper;
import org.kie.api.runtime.rule.FactHandle;


public class BrakeSystemTest {
    public static void main() {
        try {
            // Kreiraj KieContainer i KieSession
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "engineKsession");

            // Kreiraj BrakeSystem objekat
            Brake brake = new Brake();
            brake.setPedalPressure(40);   // Normalna vrednost
            brake.setPadWear(10);         // Normalna vrednost
            brake.setBrakeFluid(50);      // Normalna vrednost
            brake.setState(BrakeState.NORMAL);

            FactHandle handle = kSession.insert(brake);

            // 1. Prvo pokretanje pravila
            System.out.println("=== Prvo pokretanje pravila ===");
            kSession.fireAllRules();

            // 2. Visok pritisak pedale + istrošene kočione pločice
            brake.setPedalPressure(60);
            brake.setPadWear(25);
            kSession.update(handle, brake);
            System.out.println("=== Drugo pokretanje pravila (check brake pads) ===");
            kSession.fireAllRules();

            // 3. Pločice jako istrošene -> produžena kočiona putanja
            brake.setPadWear(55);
            kSession.update(handle, brake);
            System.out.println("=== Treće pokretanje pravila (extended braking distance) ===");
            kSession.fireAllRules();

            // 4. Nizak nivo kočione tečnosti
            brake.setBrakeFluid(15);
            kSession.update(handle, brake);
            System.out.println("=== Četvrto pokretanje pravila (brake fluid leak) ===");
            kSession.fireAllRules();

            // 5. Pad pritiska pedale -> kvar glavnog cilindra
            brake.setPedalPressure(25);
            kSession.update(handle, brake);
            System.out.println("=== Peto pokretanje pravila (main cylinder failure) ===");
            kSession.fireAllRules();

            // Konačno stanje
            System.out.println("Konačno stanje kočionog sistema: " + brake.getState());

            kSession.dispose();

        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}
