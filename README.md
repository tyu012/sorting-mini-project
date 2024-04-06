# sorting-mini-project
As the name suggests, it's our mini-project for the sorting lab.

https://github.com/tyu012/sorting-mini-project

# Author
Tim Yu

# Purpose
Implementing insertion sort, merge sort, and quicksort.
Designing our own sorting algorithms.
Writing test cases for sorting algorithms.

# Acknowledgements

## Code used from labs done with others

Keely Miyamoto (merge sort lab partner)

Zack Abdilahi (quicksort lab partner)

## Online resources

Instructions for this mini-project:
https://rebelsky.cs.grinnell.edu/Courses/CSC207/2024Sp/mps/mp06.html

Java® Platform, Standard Edition & Java Development Kit
Version 17 API Specification,
https://docs.oracle.com/en/java/javase/17/docs/api/index.html

How to shuffle arrays (in test cases): Duncan Jones,
https://stackoverflow.com/questions/1519736/random-shuffling-of-an-array/21454317#21454317

ChatGPT (part five only; see below)

# Comments about AI usage in designing my algorithm
I begin the chat by asking how I can combine merge sort and quicksort.
I liked that the LLM provided me with a "skeleton" of the code that will let me do this,
which reduces the amount of time I need.
From here, I was able to create wrapper methods that sorted partitions.
I was also wondering how to find the best threshold value to know when to use a sorting algorithm,
but I felt like I knew that I should show it experimentally.
 
Altogether, I didn't find the AI to be very useful, since I already have a general idea of what
I am trying to do.
I think that I need to give more specific prompts to the AI for it to actually describe the
implementations. If not, I don't get examples of specific implementations.
 
One thing that I found useful was being able to ask questions about what methods to use for
doing an experiment.
For example, I can ask the AI for classes which allow me to time how fast the algorithm runs.