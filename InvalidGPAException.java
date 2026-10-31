package project.exception;

/**
 * Custom checked exception thrown when a GPA value is out of the valid
 * range (0.0 – 4.0).  Demonstrates Exception Handling as required by
 * the project specification.
 */
public class InvalidGPAException extends Exception {

    public InvalidGPAException(String message) {
        super(message);
    }
}
