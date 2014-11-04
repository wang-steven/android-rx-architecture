package tw.steven.project.test;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.TextView;

import com.robotium.solo.Solo;
import tw.steven.project.MainActivity;
import tw.steven.project.R;

public class BookFragmentTest extends ActivityInstrumentationTestCase2<MainActivity> {
    private Solo solo;

    public BookFragmentTest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        solo = new Solo(getInstrumentation(), getActivity());
    }

    public void test_shouldShowBookTitle() throws Exception {
        TextView titleText = (TextView) solo.getView(R.id.title);
        assertEquals(View.VISIBLE, titleText.getVisibility());
        assertNotSame("How Rx will Change the World", titleText.getText().toString());
        solo.sleep(1000);
        assertEquals("How Rx will Change the World", titleText.getText().toString());
    }
}
