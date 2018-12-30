# Math++
## Simple Java utility with a bunch of mathematical functions
[![Build Status](https://travis-ci.org/modscleo4/mathpp.svg?branch=master)](https://travis-ci.org/modscleo4/mathpp)

Math++ provides functions from number base conversion to Matrix operations, including determinant, matrix multiplication, sum, subtraction, transposing and inverting.

There's a library which you can use in your own app, including Android apks.

All the functions were documented, so all you have to do is put it in your library path and use its functions.

### Library functions (example usage)

```java
public class Main {
    public static void main(String[] args) {
        Matrix mat = new Matrix({
            {1, 2, 3},
            {4, 5, 6},
            {7, 8, 9}
        });
        System.out.println("Determinant: " + mat.determinant());
        System.out.println("Is a triangle matrix? " + mat.isTriangle());
    }
}
```

