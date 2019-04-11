# MiningCalculator

A calculator app that helps Star Citizen miners determine the value of rocks before deciding to mine them.


**TODO:** 
1. Reset current allocation
1. Allow editing of committed chunk
1. Prevent adding empty chunks
1. Improve value selection method. Pickers seem awkward for numbers. Selection of chunk sizes is inadequate.
1. Need finer input of allocation percentage. Whole numbers are inadequate.
1. Start with initial default value of 100% Inert.
1. Reduce the remaining allocation percentages in the picker after an allocation is set
1. Remove a set ore from the picker to prevent dupes.
1. Add a distinctive name for each chunk
1. Add a mining area filter. Include as part of chunk name
1. ~~Calculate chunk value for main screen.~~
1. ~~Calculate total value for all runs on main screen.~~
1. ~~Change chunk Id at right time, so each allocation list is accurate to each chunk~~
1. ~~Add total value of chunk to chunk define screen.~~
1. ~~Hook up the reset runs menu button to clear the data for the app.~~
1. ~~Fix the ore allocation value amounts. They look wrong.~~
1. ~~Find Ore from the Ore db rather than newing a fake one.~~

**USER FLOW:**
1. Pick PLANETOID from a list
   * This filters ORES available
   * Sets part of the RUN title
1. Start a new RUN
   * Assign PLANETOID + DATETIME as RUN id
1. Add a new mineable ROCK
   * Assign a number (coords?) for the ROCK id
   * Enter the total MASS from scanning data
   * Enter ALLOCATION for each ORE
   * Calculate potential VALUE
   * Select KEEP or DISCARD actions
1. Add a new ROCK CHUNK
   * Assign a letter for the CHUNK id
   * Enter MASS
   * Enter ALLOCATION for each ORE
   * Calculate VALUE
   * Select KEEP or DISCARD action
     * KEEP accumulates the VALUE to the ROCK mined VALUE
     