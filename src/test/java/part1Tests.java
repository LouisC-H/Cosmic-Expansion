import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class part1Tests {


    @Test
    void simplestGalaxy(){
        Assertions.assertEquals(1, Main.runDay11Code("src/main/resources/testInputs/simplestGalaxy.txt", 2));
    }
    // with one empty col and row this time
    @Test
    void simplestGalaxy2(){
        Assertions.assertEquals(6, Main.runDay11Code("src/main/resources/testInputs/simplestGalaxy2.txt", 2));
    }
    //more realistic
    @Test
    void basicGalaxy(){
        Assertions.assertEquals(42, Main.runDay11Code("src/main/resources/testInputs/basicGalaxy.txt", 2));
    }
    @Test
    void exampleP1(){
        Assertions.assertEquals(1030, Main.runDay11Code("src/main/resources/example1.txt", 1000000));
    }
}
