# The Basic Guide to Building an Asset Hierarchy for Toronto Water

## Hard and Fast Rules

1. Every entity must have a parent entity
2. Every entity must only have one parent entity
3. Every parent entity must be either
  4. exist in Toronto Water's asset database <sup>[1](#myfootnote1)</sup>, previous to the start of the project, or
  5. exists in the EIW or AIW, in virtue of the consultant's addition 

## Specification at the Lower Levels of the Hierarchy

If an entity is found on a line, think of a shut-off valve or a flow sensor on a pump line for example, then the entity's parent should be the most important asset on that line. In the case of this example, it is the pump, which performs the primary function on the line - and the valve exists to serve the pump. We use the term *line* to denote a set of assets connected in series, by some linear elements, such as pipes and cables. 

If the entity already performs the most important function on the line, say the pump, then its parent should be a **system entity**.<sup>[1](#myfootnote1)</sup>, <sup>[2](#myfootnote2)</sup>  For example, the pump would be a child of the sludge removal pump system or a lubrication pumping system.  Note that a system entity could be small.  A set of parallel lineup of equipment, providing some function, such as pumping, mixing, heating. etc, would constitute a system entity, at the lowest level.

If a line does not have an asset that is more prominent than any other, (instead let's say it only has valves performing flow control), then the parent of these valves could be a system entity as well.  Valves on the headers joining parallel (pump) lines of a (pumping) system, often fall into this case. 

## Specifying Middle Levels of the Hierarchy

If the entity is a system, then its parent must be a larger system (or a collection<sup>[3](#myfootnote3)</sup> of systems).  Here are a couple of requirements to keep in mind, when specifying a parent system.  

1. a parent system must physical contains all parts of the smaller system, and 
2. every higher-level function of the parent must be supported by the functions of its smaller system parts. 

The instruction above is predicated on the fact that the consultant would need to specifying entities that represents systems, and collections of systems, as parents of lower level assets.  [FHA-CLA] and [FIS-ELS] are examples of high level systems; {THC-PCS} and {TAB-OCS} are examples of high level system collections.  **There is currently no standard on the entity numbering nomenclature for systems.** Toronto Water's Instrumentation Specification Standards, 13040 - EQUIPMENT AND DATA TAGGING document does not apply to system entities, but only for individual assets.  The ASMP group at Toronto Water is working to close this gap.  For the time being, consultants have the liberty to propose system entity numbers that are reasonable in the EIW or AIW. All system entities must be added as a new entity in the EIW or AIW. 

## The Upper Hierarchy

The highest levels of a facilities hierarchy follows a template found in Appendix A.

The **facility system** entity, **[TFHX-PRC]** in Appendix A, is the most important entity.  It is the highest level system responsible for the primary function of the facility – i.e. water or wastewater treatment or major waste-product handling for Toronto Water's major facilities.  The facility system is made up of sub-system, also systems themselves, which directly interact with the water or a waste-product, at the various stages of their processing.  Secondary aeration system, disinfection system, and digestion system are example of high level sub-systems. (The highest level systems are often called *processes*.) 

The **plant process support system collection** **{TFHX-FPS}** and the **plant spatial support system collection** **{TFHX-FSS}** and are two collections <sup>[3](#myfootnote3)</sup> of systems that play the secondary support functions, supporting the [TFHX-PRC] system and the facility staff.  We can refer to these collections, collectively, as the **facility support systems**.  The {TFHX-FSS} collection include members systems that provide material resource supply, energy resource supply, or control functions to multiple subsystem of the [TFHX-PRC].  We often call these utility functions.  Plant water system, odour control system, electrical distribution system, emergency power systems, and the process control systems are some of the members in this collection.  The {TFHX-FSS} collection include members that support the access, optimal operating environment, and safety of the various indoor and outdoor spaces in the facility.  Security, HVAC, fire protection, and building automation system collections are some of the members in this set. 

**When specifying the hierarchy, the consultant must group assets that belong to the various facility support systems into separate groups,** such that they can be easily integrated under the sub-branches of the upper hierarchy, shown in Appendix A.  **These asset must not be not be mixed with the process system assets, under the [TFHX-PRC] branch of the hierarchy**. 

## System and System Collection Breakdown

The [TFHX-PRC] system is broken down to its high-level sub-systems, and then to more granular sub-systems, until we reach the level of lines and individual assets, at the bottom of the hierarchy.  This was covered at a high-level in this guide.  Toronto Water is currently working on a more detailed guide.

For the {TFHX-FPS} and the {TFHX-FSS} system collection, such as the odour control system or the HVAC system collection, we could continue to break down them down in the next level, into sub-collections of system - such as the collection of all HVAC systems in Section A of the facility.  However, within a couple of levels, we should reach the individual support system entities, each serving a certain sub-system of the [TFHX-PRC] or a certain building or floor space of the facility.  Examples of individual systems include, odour control system for primary treatment or HVAC system for the Z-building. 

Going further down the {TFHX-FPS} or the {TFHX-FSS} branches.  The individual systems can be broken down to sub-systems, in the same manner that the [TFHX-PRC] is broken down.

# Appendix A - Facility Upper Hierarchy
  
The following table presents a template of of the facility upper hierarchy. "TFHX" in the Entity Number column are the standin character for a Toronto Water's facility code, e.g. TAB, THC, FHA.  The "...." character indicates a level below the root the hierarchy. The curly brackets around an entity's name, such as {entity number}, signify that the entity represents collection. The square bracket, such as [entity number] signifies that the entity represents a system.

| Level | Entity Number                      | Entity Name                                                  |
| ----- | ---------------------------------- | ------------------------------------------------------------ |
| 1     | {TFHX}                             | Asset Collection, Assets of the TFHX facility                |
| 2     | .... [TFHX-PRC]                    | System, Facility  System, Wastewater Treatment Function      |
| 2     | .... {TFHX-FSS}                    | System Collection,  Facility Wide Spatial Support Function   |
| 3     | .... .... {THS-ACC}                | System Collection, Building  Space HVAC Function             |
| 3     | .... ....  {TFHX-FIRE-FEQ,LTX,LTG} | System Collection,  Evacuation, Fire Prevention, and Fire Suppression |
| 3     | .... .... {TFHX-AMT}               | System Collection,  Atmosphere Monitoring Function           |
| 3     | .... .... {TFHX-SS}                | System Collection,  Physical Security Function               |
| 3     | .... ....  {TFHX-STW,FSW}          | System Collection,  Sanitary and Storm Water Handling Functions |
| 3     | .... .... {TFHX-GRK}               | System Collection,  Facility Ground Service                  |
| 2     | .... {TFHX-FPS}                    | System Collection,  Facility Wide System Support Function    |
| 3     | .... .... {TFHX-MULTI}             | System Collection,  Multi-functional Equipment Unit          |
| 3     | .... .... {TFHX-ELS}               | System Collection,  Electrical Distribution Function         |
| 3     | .... .... {TFHX-EMP}               | System Collection,  Emergency Power Supply Function          |
| 4     | .... .... ....  {TFHX-UPS,BAT}     | System Collection,  Emergency Power Supply Function          |
| 3     | .... .... {TFHX-PCS}               | System Collection,  Process Control Function                 |
| 3     | .... .... {TFHX-OCS}               | System Collection,  Odour Control Function                   |
| 3     | .... .... {TFHX-CW}                | System Collection,  City Water Supply Function               |
| 3     | .... .... {TFHX-PWA}               | System Collection,  Plant Water Supply Function              |
| 3     | .... .... {TFHX-NG}                | System Collection,  Facility Natural Gas Supply Function     |
| 3     | .... .... {TFHX-SA}                | System Collection,  Service Air, Non-Instrument Supply Function |
| 3     | .... .... {TFHX-AI}                | System Collection,  Instrument Air Supply Function           |
| 3     | .... .... {TFHX-PA}                | System Collection,  Process Air Supply Function              |
| 2     | .... {TFHX-ARC}                    | Asset Collection,  Buildings, Architectural Parts, Structural Parts of Building |
| 3     | .... .... {TFHX-BLDG}              | Asset Collection,  Buildings                                 |
| 3     | .... .... {TFHX-ARCP}              | Asset Collection,  Architectural and Structural Parts        |
| 2     | .... {TFHX-TOOL}                   | Asset Collection,  Tools and Stationary Service Equipment    |
| 3     | .... .... {TFHX-MCS}               | Asset Collection,  Stationary and Workshop Tools             |
| 3     | .... .... {TFHX-HTOOL}             | Asset Collection,  Mobile and Hand Tools                     |
| 2     | .... {THC-VEH}                     | Asset Collection,  Vehicles                                  |

# Notes

<a name="myfootnote1">1</a>: Note this requirement will change in the future.  For now, it is kept in continuity with the present practice

<a name="myfootnote2">2</a>: a set of causally linked assets, interconnected by linear assets or wireless information links, which as a whole possesses one or more higher-level function. A system entity's number should be enclosed in square brackets, i.e. [entity number].

<a name="myfootnote3">3</a>: A collection a set of distinct units, which are unified under the collection by some description of the set - in our case, the entity name of the collection.  Each units can be a systems or individual assets. A system entity's number should be enclosed in curlly brackets, i.e. {entity number}.

<a name="myfootnote4">4</a>: The relevant scope of assets should be exported to the consultant at the beginning of the project. If it is not, the consultant should contact the ASMP reliability engineering group, via its Toronto Water project manager. 
