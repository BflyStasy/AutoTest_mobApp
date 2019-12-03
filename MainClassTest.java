import org.junit.Assert;
import org.junit.Test;

import static com.sun.javafx.util.Utils.contains;

public class MainClassTest extends MainClass
{
    @Test
    public void testGetClassString()
    {
        Assert.assertTrue("Слово 'Hello'/'hello' не является подстрокой",contains(this.getClassString(),"Hello")||contains(this.getClassString(),"hello"));
    }
}
