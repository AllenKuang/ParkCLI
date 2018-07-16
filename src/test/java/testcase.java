
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


public class testcase {
    @Test
    public void should_return_1_when_input_1(){
        int number=1;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("1"));
    }

    @Test
    public void should_return_2_when_input_2(){
        int number=2;
        Fizz fizz=new Fizz();

        String result=fizz.fizz(number);

        assertThat(result,is ("2"));
    }

    @Test
    public void should_return_Fizz_when_input_3(){
        int number=3;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("Fizz"));
    }

    @Test
    public void should_return_Fizz_when_input_6(){
        int number=6;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("Fizz"));
    }

    @Test
    public void should_return_Buzz_when_input_5(){
        int number=5;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("Buzz"));
    }
    @Test
    public void should_return_FizzBuzz_when_input_15(){
        int number=15;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("FizzBuzz"));
    }
    @Test
    public void should_return_BuzzWhizz_when_input_21(){
        int number=21;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("FizzWhizz"));
    }

    @Test
    public void should_return_FizzBuzzWhizz_when_input_105(){
        int number=105;
        Fizz fizz=new Fizz();
        String result=fizz.fizz(number);
        assertThat(result,is ("FizzBuzzWhizz"));
    }


}
