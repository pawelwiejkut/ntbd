package test.DataFactory.impl;

import test.DataFactory.ProjectDataValues;

/**
 * Created by pawelwiejkut on 13.03.2016.
 */
public class DefaultProjectDataValues implements ProjectDataValues {

    private static String[] projectName= {"FK","WWW","EDI","SAP"};
    private static String[] projectSubject={"System finansowo-księgowy","System wymiany dokumnetów","Portal korporacyjny","System ERP","System MRP"};

    public String[] getProjectName() {
        return projectName;
    }

    public String[] getProjectSubject() {
        return projectSubject;
    }
}
