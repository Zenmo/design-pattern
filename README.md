Design Patterns
===

Doel: modulaire code. Blokjes die we in elkaar kunnen schuiven.

Overzicht:

* Uitleg Dependency Inversion
* Aantal patterns a.d.h.v. code
* OO Clean code
* Value objects a.d.h.v. code



Dependency Inversion
---

Wat willen we? **Dependency Inversion**

Wat hebben we? [Procedurele code](./src/main/java/com/zenmo/procedural/Main.java)

Tussenstap: [Parameterization](./src/main/java/com/zenmo/parameterized/Main.java)

Dependency Inversion: gebruik van interface: [Agent Interface](./src/main/java/com/zenmo/agentinterface/Main.java)



Factory pattern
---

Applicatie geeft een "Factory" om een service te configureren.  [Agent Factory](./src/main/java/com/zenmo/agentfactory/Main.java)

Veelvuldig gebruik in frameworks.



DI Container
---

DI = Dependency Injection

Factories worden soms samen geregistreerd in 1 library class. 

Kleine aanpassingen kunnen gedaan worden met decorators.

(geen code voorbeeld)



DTO / record / data class
---

Gebruikt om data te transporteren tussen componenten van de applicatie.

[Voorbeeld](src/main/java/com/zenmo/dto/Agent.java)

Voorbeeld componenten: UI, engine, database, API client, API server.

Voorkomt OOP valkuil: **Fat Model**

Enum wordt vaak gebruikt in een DTO. Gebruik van enum in kernlogica is een anti-pattern.



Strategy pattern
---

Bv: batterij gedrag, reisgedrag, [overbelasting](src/main/java/com/zenmo/loadstrategy/ElectricGridNode.java) etc.



Observer pattern
---

Bv: UI componenten die een model weergeven. [ElectricCar](src/main/java/com/zenmo/carobserver/ElectricCar.java)

Generiekere verise: event bus.

Communicatie andersom: command bus.



Concurrency patterns
---

* Job (bv in Unity game engine)
* Queue



OO Clean code
===


Encapsulation
---

* object is altijd in een geldige staat
    * validatie in/door constructor
    * lastig? -> builder pattern -> idee voor in AnyLogic? (voorbeeld als tijd over is)
* aanpassingen aan de eigenschappen van een object gaan via de methodes van het object.



Law of Demeter
---

Een object communiceert alleen via properties of parameters.

Geen globals, singletons, statics.

Niet object bomen aflopen of diepe introspectie doen:

```jshelllanguage
for (var gridConnection: this.gridConnections) {
    for (var asset : gridConnection.getAssets()) {
        if (asset instanceof Battery) {
            // do stuff with battery
        }
    }
}
```



Composition
---

Bouw complexe objecten door ze samen te stellen uit kleinere objecten.

Beperk overerving.

Beperk het aantal properties in een object.



Tell, don't ask
---

"Een object is geen trekpop"

Gedrag en data gaan samen in een object.

Niet te ver doorvoeren. Af en toe een getter of setter mag.



Value Object
---

Heilige graal van het programmeren: immutability.

[Value Object](src/main/java/com/zenmo/powervalue/Main.java)

Er zijn geen enteiten meer, alleen maar waarden. De entiteit wordt een verzameling van waarden door de tijd.

Het programma is "puur": een serie van transformaties van data.

Nooit race conditions: race conditions zijn alleen mogelijk als een object veranderd wordt.
