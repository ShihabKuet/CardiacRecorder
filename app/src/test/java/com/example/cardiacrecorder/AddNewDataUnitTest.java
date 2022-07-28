package com.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertFalse;
        import static org.junit.Assert.assertThrows;
        import static org.junit.Assert.assertTrue;

        import org.junit.Test;

        import java.util.Calendar;
        import java.util.Date;


public class AddNewDataUnitTest {

    /**
     * testing adding method
     */
    @Test
    public void testAddData() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(74,140,72,"Id1","after meal",date);
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        AddNewData data2 = new AddNewData(78,134,63,"Id2","after walk",date);
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));
    }

    /**
     * testing delete method
     */
    @Test
    public void testDeleteData() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(74,140,72,"Id1","after meal",date);
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        AddNewData data2 = new AddNewData(78,134,63,"Id2","after walk",date);
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));

        dataList.deleteData(data1);
        assertEquals(1, dataList.getData().size());
        assertFalse(dataList.getData().contains(data1));

    }

    /**
     * testing addData exceptions
     */
    @Test
    public void testAddRecordException() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(77,129,76,"Id5","after dinner",date);
        dataList.addData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.addData(data1));
    }

    /**
     * testing deleteData exceptions
     */
    @Test
    public void testDeleteRecordException() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(69,128,76,"Id6","before eating",date);
        dataList.addData(data1);

        dataList.deleteData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.deleteData(data1));
    }
}