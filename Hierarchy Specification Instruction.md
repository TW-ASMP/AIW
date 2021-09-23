# Guide on Identifying the Correct Parent Entity

## Lower Levels of the Hierarchy

If an entity is found on a line, think of a shut-off valve or a flow sensor on a pump line for example, then the entity's parent should be the most important asset on that line. In the case of this example, it is the pump, which performs the primary function on the line - and the valve exists to serve the pump. We use the term *line* to denote a set of assets connected in series, by some linear elements, such as pipes and cables. 

If the entity already performs the most important function on the line, say the pump, then its parent should be a **system entity**.<sup>[1](#myfootnote1)</sup>, <sup>[2](#myfootnote2)</sup>  For example, the pump would be a child of the sludge removal pump system or a lubrication pumping system.  Note that a system entity could be small.  A set of parallel lineup of equipment, providing some function, such as pumping, mixing, heating. etc, would constitute a system entity, at the lowest level.

If a line does not have an asset that is more prominent than any other, (instead let's say it only has valves performing flow control), then the parent of these valves could be a system entity as well.  Valves on the headers joining parallel (pump) lines of a (pumping) system, often fall into this case. 

## Middle Levels of the Hierarchy

If the entity is a system, then its parent must be a larger system (or a collection<sup>[3](#myfootnote3)</sup> of systems).  Here are a couple of requirements to keep in mind, when specifying a parent system.  

1. a parent system must physical contains all parts of the smaller system, and 
2. every higher-level function of the parent must be supported by the functions of its smaller system parts. 

## Levels of the Hierarchy

The highest levels of a facilities hierarchy follows a template found in Appendix A.

The **facility system** entity, **[TFHX-PRC]** in Appendix A, is the most important entity.  It is the highest level system responsible for the primary function of the facility – i.e. water or wastewater treatment or major waste-product handling for Toronto Water's major facilities.  The facility system is made up of sub-system, also systems themselves, which directly interact with the water or a waste-product, at the various stages of their processing.  Secondary aeration system, disinfection system, and digestion system are example of high level sub-systems. (The highest level systems are often called *processes*.) 

The **plant process support system collection** **{TFHX-FPS}** and the **plant spatial support system collection** **{TFHX-FSS}** and are two collections <sup>[3](#myfootnote3)</sup> of systems that play the secondary support functions, supporting the [TFHX-PRC] system and the facility staff.  We can refer to these collections, collectively, as the **facility support systems**.  The {TFHX-FSS} collection include members systems that provide material resource supply, energy resource supply, or control functions to multiple subsystem of the [TFHX-PRC].  We often call these utility functions.  Plant water system, odour control system, electrical distribution system, emergency power systems, and the process control systems are some of the members in this collection.  The {TFHX-FSS} collection include members that support the access, optimal operating environment, and safety of the various indoor and outdoor spaces in the facility.  Security, HVAC, fire protection, and building automation system collections are some of the members in this set. 

**When specifying the hierarchy, the consultant must group assets that belong to the various facility support systems into separate groups,** such that they can be easily integrated under the sub-branches of the upper hierarchy, shown in Appendix A.  **These asset must not be not be mixed with the process system assets, under the [TFHX-PRC] branch of the hierarchy**. 

## System and System Collection Breakdown

The [TFHX-PRC] system is broken down to its high-level sub-systems, and then to more granular sub-systems, until we reach the level of lines and individual assets, at the bottom of the hierarchy.  This was covered at a high-level in this guide.  Toronto Water is currently working on a more detailed guide.

For the {TFHX-FPS} and the {TFHX-FSS} system collection, such as the odour control system or the HVAC system collection, we could continue to break down them down in the next level, into sub-collections of system - such as the collection of all HVAC systems in Section A of the facility.  However, within a couple of levels, we should reach the individual support system entities, each serving a certain sub-system of the [TFHX-PRC] or a certain building or floor space of the facility.  Examples of individual systems include, odour control system for primary treatment or HVAC system for the Z-building. 

Going further down the {TFHX-FPS} or the {TFHX-FSS} branches.  The individual systems can be broken down to sub-systems, in the same manner that the [TFHX-PRC] is broken down.

The instruction above is predicated on the fact that you would need to specifying entities that represents systems, and collections of systems. These entities will be the parents of asset entities and line entities that make up the system.  [FHO-CLA] and [FHO-ELS] are examples of the high level system. **There is currently no standard on the entity numbering nomenclature for low level systems.** The 13040 document does not apply to system entities, but only for individual assets.  The ASMP group at Toronto Water is working to close this gap.  You have the liberty to proposing an entity number that is reasonable in the EIW. Whatever new system entity that you propose as a parent should also be added as a new entity in the sheet named *1. General Information*.

# Appendix A - Facility Upper Hierarchy
  
The curly brackets around an entity's name, such as {entity number}, signify that the entity represents collection. The square bracket, such as [entity number] signifies that the entity represents a system.

| Entity Number                  | Entity name                                                  |
| ------------------------------ | ------------------------------------------------------------ |
| {TFHX}                         | Asset Collection, All Assets of TFHX facility                |
| .  **[TFHX-PRC]**              | System, Facility System,  Wastewater Treatment Function      |
| .  **{TFHX-FSS}**              | System Collection, Facility Wide  Spatial Support Function   |
| .  .   {THS-ACC}               | System Collection, Building Space HVAC Function              |
| .  .   {TFHX-FIRE-FEQ,LTX,LTG} | System Collection, Evacuation,  Fire Prevention, and Fire Suppression |
| .  .   {TFHX-AMT}              | System Collection, Atmosphere  Monitoring Function           |
| .  .   {TFHX-SS}               | System Collection, Physical  Security Function               |
| .  .   {TFHX-STW,FSW}          | System Collection, Sanitary and  Storm Water Handling Functions |
| .  .   {TFHX-GRK}              | System Collection, Facility  Ground Service                  |
| .  **{TFHX-FPS}**              | System Collection, Facility Wide  System Support Function    |
| .  .   {TFHX-MULTI}            | System Collection,  Multi-functional Equipment Unit          |
| .  .   {TFHX-ELS}              | System Collection, Electrical Distribution Function          |
| .  .   {TFHX-EMP}              | System Collection, Emergency Power Supply Function           |
| .  .   .  {TFHX-UPS,BAT}       | System Collection, Emergency Power Supply Function           |
| .  .   {TFHX-PCS}              | System Collection, Process Control Function                  |
| .  .   {TFHX-OCS}              | System Collection, Odour Control Function                    |
| .  .   {TFHX-CW}               | System Collection, City Water Supply Function                |
| .  .   {TFHX-PWA}              | System Collection, Plant Water Supply Function               |
| .  .   {TFHX-NG}               | System Collection, Facility Natural Gas Supply Function      |
| .  .   {TFHX-SA}               | System Collection, Service Air,  Non-Instrument Supply Function |
| .  .   {TFHX-AI}               | System Collection, Instrument  Air Supply Function           |
| .  .   {TFHX-PA}               | System Collection, Process Air  Supply Function              |
| .  {TFHX-ARC}                  | Asset Collection, Buildings,  Architectural Parts, Structural Parts of Building |
| .  .   {TFHX-BLDG}             | Asset Collection, Buildings                                  |
| .  .   {TFHX-ARCP}             | Asset Collection, Architectural  and Structural Parts        |
| .  {TFHX-TOOL}                 | Asset Collection, Tools and  Stationary Service Equipment    |
| .  .   {TFHX-MCS}              | Asset Collection, Stationary and  Workshop Tools             |
| .  .   {TFHX-HTOOL}            | Asset Collection, Mobile and  Hand Tools                     |
| .  {TFHX-VEH}                  | Asset Collection, Vehicles                                   |



# Notes


<a name="myfootnote1">1</a>: Note this requirement will change in the future.  For now, it is kept in continuity with the present practice

<a name="myfootnote2">2</a>: a set of causally linked assets, interconnected by linear assets or wireless information links, which as a whole possesses one or more higher-level function. A system entity's number should be enclosed in square brackets, i.e. [entity number].

<a name="myfootnote3">3</a>: A collection a set of distinct units, which are unified under the collection by some description of the set - in our case, the entity name of the collection.  Each units can be a systems or individual assets. A system entity's number should be enclosed in curlly brackets, i.e. {entity number}.
