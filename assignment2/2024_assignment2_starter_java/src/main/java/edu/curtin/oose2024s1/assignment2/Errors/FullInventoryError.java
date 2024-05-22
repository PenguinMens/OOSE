
package edu.curtin.oose2024s1.assignment2.Errors;

/**
 * FullBikeError
 */
class FullInventoryError extends Exception{

    public FullInventoryError(String message) {
        super(message);
    }
    
    public FullInventoryError(String message, Throwable cause) {
        super(message, cause);
    }
}