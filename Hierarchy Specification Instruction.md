# Basic Guide to Building an Asset Hierarchy for Consultants
<sup>Written by Tony Huang, Asset Strategy and Maintenance Planning Unit, Toronto Water.</sup>

## Terms

`System` - a set of causally linked asset parts, interconnected by linear assets or wireless information links, which as a whole possesses one or more higher-level functions. A system entity's number should be enclosed in square brackets, i.e. [entity number].

`Collection` - a set of distinct units, which are unified under a definition of the set - in our case, the entity name of the collection.  Each unit can be a system or individual assets. A system entity's number should be enclosed in curly brackets, i.e. {entity number}.

`You` - in the context of the document, refers to the consult

`We/us` - refers to Toronto Water

## Hard-and-Fast Rules of Hierarchy Specification

* Every entity, with the exception of the enity that represents an entire facility, must have one and only one parent eneity
* Every entity that is specified as a parent entity must either
  * exist in Toronto Water's asset database (the Work Management System or WMS)<sup>[1](#myfootnote1)</sup>, previous to the start of the project, or
  * exists in the EIW or AIW spreadsheet, in virtue of the consultant's addition into the spreadsheet
* An entity's parent may not be 
  * the entity itself,
  * any child of the entity, or in more general terms,
  * any of the entity's decendents in the hierarchy

## Specification at the Lower Levels of the Hierarchy

At the lowest level, if an entity is found on a line, think of a shut-off valve or a flow sensor on a pump line for example, then the entity's parent should be the most important asset on that line. In the case of this example, it is the pump, which performs the primary function on the line.  The valve exists to serve the pump. Finally just to clarify, we use the term *line* to denote a set of assets connected in series, by some linear elements, such as pipes and cables. 

If the entity already performs the most important function on the line, say the pump, then its parent should be a **system entity**.<sup>[2](#myfootnote2)</sup>.  For example, a pump would be a child of the sludge removal pumping system or a certain lubrication pumping system.  A system entity could be small.  A** set of parallel lineups of equipment, providing some function, such as mixing, heating, or pumping would constitute a low level system entity.**

If a line does not have an asset that is more prominent than any other asset on that line, and instead, let's say it only has valves performing flow control, then the parent of these valves should be the system entity as well.  Valves on the headers joining parallel (pump) lines of a (pumping) system would fall into this case. 

## Specifying Middle Levels of the Hierarchy

If the entity is a system, then its parent should be a larger system or a collection of systems.  There is a requirement to keep in mind when specifying a parent system, and which is _every part of the smaller system must be a physical part of the parent system_.

If you are a consultant who has worked with us in the past, you would've seen system level entities in our database (the WMS) and our drawings. FHA-CLA and FIS-ELS are examples of high-level systems; THC-PCS and TAB-OCS are examples of high-level system collections.  **There is currently no standard on the entity numbering nomenclature for systems or collections.** Toronto Water's Instrumentation Specification Standards, 13040 - EQUIPMENT AND DATA TAGGING document, which you may have reference for designating a new entity number, does not apply to system level entities. It only applies to single assets.  The ASMP group at Toronto Water is working to close this gap.  For the time being, you have the liberty to propose system entity numbers that are reasonable in the EIW or AIW. All system level entities must be added as a new entity in the EIW or AIW. The only convention that we'd like you to apply is to use square brackets to enclose system entity numbers and use curly brackets to enclose system collection entity numbers. For example, [FHA-CLA], [FIS-ELS], {THC-PCS}, and {TAB-OCS} are the new proper forms. 

## The Upper Hierarchy

The highest levels of a facilities hierarchy follows a template found in Appendix A.

The **facility process system** entity, **[TFHX-PRC]** in Appendix A, is the most important entity.  It is the highest level system responsible for the primary function of the facility – i.e. water or wastewater treatment or major waste-product handling for our major facilities.  The facility process system is made up of sub-system, which we also recognize as systems. These facility process systems directly interact with the water or a waste-product, at the various stages of their processing. The disinfection system and digestion system are examples of its high-level sub-systems. If you hear us using the term *processes*, take it to mean a high-level subsystem of the [TFHX-PRC].

The **facility process support system collection** **{TFHX-FPS}** and the **facility spatial support system collection** **{TFHX-FSS}** and are two system collections that play the secondary support roles to the [TFHX-PRC] and the facility's staff.  We can refer to these collections collectively as the **facility support systems**.  

The {TFHX-FPS} collection includes member systems that provide material resource supply, energy resource supply, or control functions to multiple subsystems of the [TFHX-PRC].  We often call these utility functions.  Plant water system, odour control system, electrical distribution system, emergency power systems, and the process control systems are some of the members in this collection.

The {TFHX-FSS} collection includes members that support the access, optimal operating environment, and safety of the various indoor and outdoor spaces in the facility.  Security, HVAC, fire protection, and building automation system collections are some of the members in this collection. 

**When specifying the hierarchy, you must group assets that belong to the various facility support systems into separate groups,** such that they can be easily integrated under the sub-branches of the upper hierarchy, shown in Appendix A.  **These asset must not be not be mixed with the process system assets, under the [TFHX-PRC] main branch of the hierarchy**. 

## A Review of System and System Collection Hierarchical Breakdown

To reiterate some contents above, [TFHX-PRC] system is broken down to its high-level sub-systems, and then to more granular sub-systems, until we reach the level of lines and individual assets, at the bottom of the hierarchy. We are currently working on a more detailed guide, which should be available by the end of 2021.

For the {TFHX-FPS} and the {TFHX-FSS} system collection, such as the odour control system or the HVAC system collection, we could break these collections down into sub-collections of system - such as the collection of all HVAC systems in Section A of a facility.  However, within a couple of levels of the hierarchy, we should reach the individual support system entities, each serving a certain sub-system of the [TFHX-PRC] or a certain building space of the facility.  Examples of individual support systems entities include, odour control system for primary treatment or HVAC system for the Z-building. 

Going even further down the {TFHX-FPS} or the {TFHX-FSS} branches.  The individual systems should also be broken down into subsystems, and then lines and individual assets, in the same manner that the [TFHX-PRC] is broken down.

# Appendix A - The Facility Upper Hierarchy
  
The following table presents a template of the facility upper hierarchy. "TFHX" in the Entity Number column is a piece of stand-in text for a facility code, e.g. TAB, THC, FHA.  The "...." text indicates a level below the root of the hierarchy. The curly brackets around an entity's name signify that the entity represents collection. The square bracket signifies that the entity represents a system.

| Level | Entity Number                      | Entity Name                                                  |
| ----- | ---------------------------------- | ------------------------------------------------------------ |
| 1     | {TFHX}                             | Asset Collection, Assets of the TFHX facility                |
| 2     | .... [TFHX-PRC]                    | System, Facility Process System, Wastewater Treatment Function      |
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
| 4     | .... .... ....  {TFHX-UPS}         | System Collection,  Battery Powered Emergency Power Supply Function          |
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
| 3     | .... .... {TFHX-HTOOL}             | Asset Collection,  Portable and Hand Tools                   |
| 2     | .... {THC-VEH}                     | Asset Collection,  Vehicles                                  |

# Note

<a name="myfootnote1">1</a>: The relevant scope of assets should be exported to you at the beginning of the project. If it is not, you should contact the ASMP reliability engineering group, via your Toronto Water project manager. 

<a name="myfootnote2">2</a>: Note this requirement will change in the future.  For now, it is kept in continuity with the present practice
