# CISC475 Project
OCR Statistical Comparative Analysis
Team 5: Talha Ehtasham, Sam Flomenberg, Pravallika Santhil, Jae Yoo, Gary Sidoti


https://github.com/McMerrison/CISC475-Project 
## Requirements
Prompt user for different functionality:
show average score of a given OCR: given an OCR, test images, test keys, give the average score of an OCR across all test images.
Save some or all data to text file.
compare against other OCRs: given average score of an OCR across all test images, aggregate scores.
Tabulate scores across OCRs by image: Tabulate scores across OCRs by image: show all images in rows and different OCRs in columns; populate cells with scores.
## Design
### Pseudocode: 
While Testing:
Read command line input: OCR_Program (arg 1) optional flag (images) (test keys)
	Runtime()
Redirect output of OCR program to a data structure 
	String[] subjects = new String[len]
Build array of expected outputs for each image (provided by user)
	String[] keys = new String[len]
Compare test data structure with key data structure
	Needleman-Wunsch Algorithm
Create array of scores, assign to test data structure
	int scores_OCRname[] = new int[len]
	scores_OCRname[n] = compare(subject[n], key[n]) "\n"
Prompt for next OCR


### While Analyzing:
Prompt user for different functionality:
1) show average score of a given OCR: given an OCR, test images, test keys, give the average score of an OCR across all test images 
2) compare against other OCRs: given average score of an OCR across all test images, aggregate scores
3) tabulate scores across OCRs by image: Tabulate scores across OCRs by image: show all images in rows and different OCRs in columns; populate cells with scores
4) Save some or all data to text file.
Testing
We used manual testing to test the functionality of our program. The following is a list of all the design requirements for the project, and their testing status.
Read command line input: OCR_Program (arg 1) optional flag (images) (test keys)
	Runtime() - TESTED.
Redirect output of OCR program to a data structure 
	String[] subjects = new String[len] - TESTED.
Build array of expected outputs for each image (provided by user)
	String[] keys = new String[len] - TESTED.
Compare test data structure with key data structure
	Needleman-Wunsch Algorithm - TESTED.
Create array of scores, assign to test data structure
	int scores_OCRname[] = new int[len] - TESTED.
	scores_OCRname[n] = compare(subject[n], key[n]) - TESTED.
Show average score of a given OCR: given an OCR, test images, test keys, give the average score of an OCR across all test images.  - TESTED.
Compare against other OCRs: given average score of an OCR across all test images, aggregate scores.  - TESTED.
Tabulate scores across OCRs by image: Tabulate scores across OCRs by image: show all images in rows and different OCRs in columns; populate cells with scores.  - TESTED.
Prompt for next OCR  - TESTED.
