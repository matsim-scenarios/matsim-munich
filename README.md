# The MATSim Munich Scenario

### About this project

This repository provides a MATSim transport model for Munich, provided by the [Associate Professorship of Travel Behavior](https://www.mos.ed.tum.de/en/tb/) of [Technische Universität München](http://www.tum.de). The Munich MATSim scenario includes a network generated from OpenStreetMap and a plan file, which is the result of the agent-based travel demand model MITO. This model simulates travel demand at the individual resolution, based on the results of the household travel survey Mobilität in Deutschland. For further details see our prefered citation: 

 - Moeckel, Rolf, Kuehnel, Nico, Llorca, Carlos, Moreno, Ana Tsui and Rayaprolu, Hema (2020) Agent-based Simulation to Improve Policy Sensitivity of Trip-Based Models. Journal of Advanced Transportation, 2020. https://doi.org/10.1155/2020/1902162

The scenario is ultimately coming from a SILO model that is maintaned elsewhere.

### Note

Handling of large files within git is not without problems (git lfs files are not included in the zip download; we have to pay; ...).  In order to avoid this, large files, both on the input and on the output side, reside at https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/munich/munich-v1/input/. These data are open and can be downloaded.   

### Simple things (without installing/running MATSim)

### Downloading the repository alternative 1: Download ZIP

1. Click on `Clone or download` and then on `Download ZIP`.
1. Unzip the repository.
1. Go to "Download the data" below.

### Downloading the repository alternative 2: Clone the repository

##### git clone the code

1. Install git for the command line.
1. Type `git clone https://github.com/matsim-vsp/matsim-munich.git` in the command line.

(Or use your IDE, e.g. Eclipse, IntelliJ, to clone the repository.)

This will result in a new `matsim-munich` directory.  Memorize where you have put it.  You can move it, as a whole, to some other place.

##### Update your local clone of the repository.

1. Go into the `matsim-munich` directory.
1. Type `git pull`

(Or use your IDE, e.g. Eclipse, IntelliJ, to update the repository.)

This will update your repository to the newest version.

### Download the data
There is no need to download the data to run the base scenario provided by the team from TUM. The is downloaded directly when running matsim via their url. In any case, the input data can be downloaded from the svn server.  

##### svn checkout the data

1. Install svn for the command line.
1. `svn checkout https://svn.vsp.tu-berlin.de/repos/public-svn/matsim/scenarios/countries/de/munich/munich-v1/input/ `

##### update your local copy of the data

1. Go into the matsim-munich data directory.
1. Type `svn update`.

### Run the MATSim Munich scenario ...
(Requires either cloning or downloading the repository.)

##### ... using an IDE, e.g. Eclipse, IntelliJ
1. Set up the project in your IDE.
1. Make sure the project is configured as maven project.
1. Run the JAVA class `src/main/java/org/matsim/gui/MATSimGUI.java`.  A simple GUI should open.
1. In the GUI, click on the "Choose" button for configuration file.  Navigate to the `scenario/tumTb/configBase.xml` to load the configuration file.
1. Increase memory in the GUI.
1. Press the "Start MATSim" button.  This should run MATSim.  
1. "Open" the output directory.  You can drag files into VIA as explaned below.
1. Edit the config file or adjust the run class.  In particular, it makes sense to reduce the lastIteration number for trying around.  Re-run MATSim.

##### !! Path convention: !!

To work outside the GUI, you need
* the code (from github) in `<someDirectoryRoot>/<something>/matsim-munich`
* the data (from svn) in `<someDirectoryRoot>/shared-svn/projects/matsim-munich`

More technically, the code searches, from the java root, for the data by `../../shared-svn/projects/matsim-munich` .  This is a bit annoying, but everything else (e.g. git lsf, git fat) is worse.
So please move the code directory and the data directory accordingly.  


##### Run VIA on output files

1. Get VIA from https://www.simunto.com/via/.  (There is a free license for a small number of agents; that will probably work but only display a small number of vehicles/agents.)
1. Go to http://svn.vsp.tu-berlin.de/repos/shared-svn/projects/matsim-munich/scenarios/v2/output/2019-03-26-1pct-1it/ .
1. Download `*.output_network.xml.gz` and `*.output_events.xml.gz`.  Best make sure that they do not uncompress, e.g. by "Download linked file as ...".
1. Get these files into VIA.  This can be achieved in various ways; one is to open VIA and then drag the files from a file browser into VIA.
1. Run VIA and enjoy.


### More information
For more information about the Munich scenario, see here: https://www.mos.ed.tum.de/en/tb/

For more information about MATSim, see here: https://www.matsim.org/.


## Internal documentation

Internal documentation can be found here:
[https://docs.google.com/document/d/133CuXaMuWL0NcstyFHodk4y7J5yFLy9nrLtbBVpNWzM/edit?usp=drive_link](https://docs.google.com/document/d/1V1sSQmq3voY5oQ2qiXbUXU5Bn_mze2BsSg1ecyRF0rw/edit)
