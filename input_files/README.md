# LawnMower input files

The mowers are programmed using an input file constructed in the following manner:

* The first line corresponds to the upper right corner of the lawn. The bottom left corner is
implicitly (0, 0).
* The rest of the file describes the multiple mowers that are on the lawn. Each mower is described
on two lines:
    * The first line contains the mower's starting position and orientation in the format "X Y O". X and
Y are the coordinates and O is the orientation.
    * The second line contains the instructions for the mower to navigate the lawn. The instructions
are not separated by spaces.

Here is an example:
```
5 5
1 2 N
LFLFLFLFF
3 3 E
FFRFFRFRRF
```

This input file should return the following line via `stdout`:
```
1 3 N
5 1 E
```
