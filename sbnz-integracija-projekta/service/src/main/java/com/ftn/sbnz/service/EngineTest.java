package com.ftn.sbnz.service;


import com.ftn.sbnz.model.utils.KnowledgeSessionHelper;
import com.ftn.sbnz.model.Engine;
import com.ftn.sbnz.model.enums.State;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;


public class EngineTest {

    public static void main() {
        try {
            System.out.println("UDJE OVDE");
            // Kreiraj KieContainer i KieSession
            KieContainer kc = KnowledgeSessionHelper.createRuleBase();
            KieSession kSession = KnowledgeSessionHelper.getStatefulKnowledgeSession(kc, "engineKsession");

            Engine engine = new Engine();
            engine.setTemperature(90.0);
            engine.setState(State.NORMAL);
            engine.setCoolantLevel(50.0);
            engine.setFanActive(true);
            engine.setPressureDrop(false);

            // Insert i prvo pokretanje pravila
            FactHandle handle = kSession.insert(engine);
            System.out.println("=== Prvo pokretanje pravila ===");
            kSession.fireAllRules();

            // Pregrevanje
            engine.setTemperature(100.0);
            kSession.update(handle, engine);
            System.out.println("=== Drugo pokretanje pravila (pregrevanje) ===");
            kSession.fireAllRules();

            // Nizak nivo rashladne tečnosti
            engine.setCoolantLevel(30.0);
            kSession.update(handle, engine);
            System.out.println("=== Treće pokretanje pravila (niski nivo rashladne tečnosti) ===");
            kSession.fireAllRules();

            // Ventilator ne radi
            engine.setFanActive(false);
            kSession.update(handle, engine);
            System.out.println("=== Četvrto pokretanje pravila (ventilator ne radi) ===");
            kSession.fireAllRules();

            // Pad pritiska
            engine.setPressureDrop(true);
            kSession.update(handle, engine);
            System.out.println("=== Peto pokretanje pravila (pad pritiska) ===");
            kSession.fireAllRules();

            // Konačno stanje
            System.out.println("Konačno stanje motora: " + engine.getState());

            kSession.dispose();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}