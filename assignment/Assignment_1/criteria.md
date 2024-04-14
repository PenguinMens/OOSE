# F
The way the composite pattern has been used is to store the WBS information, it works in this design as it is simililar to a tree structure. Where the composite is the generic workItem and the leaf will be the Task.
This works because a task is inherintly the same as the composite workItem having an id and description. The only difference being the that the leaf is expected to have a effort associated and the composite needs to keep tracks on its children. So when the input file has 3 elements we know this is a workitem and when it has 4 it is a task. A dictionary is used to keep track of the workitems and is used to get the parent catagory to add a child task to it. 

The composite pattern is usefull for calculating aspects of the WBS, for example displaying works in a recursive fashion, where the wokritem will  display information about itself and also about its children by calling the same function for the children. This works well for other needs like calculating effort and saving the WBS.

# E
I have chosen the template method pattern to impliment the "estiamte effort" feature. This works well as there are 3 different ways to estimate effort namely median, highest, and "democracy" where users need to select the best one after estimating. Since this is the only difference a hook method can be used to change this last part of the algorithm. Creating 3 different classes for the 3 difference methods. This will easily allow for more methods to be added later on if need be. The only problem with my implimentation was where to start the "template algorithm" i have chosen for it to start after the tasks has been found and a lsit of tasks are being implimented. This felt more correct as the template method only cares about the single task. 

In code the classes are kept in a map for easy access as the class wide variable "option" is used to as the key. 