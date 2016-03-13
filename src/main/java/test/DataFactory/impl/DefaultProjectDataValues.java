package test.DataFactory.impl;

import test.DataFactory.ProjectDataValues;

/**
 * Created by pawelwiejkut on 13.03.2016.
 */
public class DefaultProjectDataValues implements ProjectDataValues {

    private static String[] projectName= {"FK","WWW","EDI"};
    private static String[] projectSubject={"System finansowo-księgowy","System wymiany dokumnetów","Portal korporacyjny"};

    public String[] getProjectName() {
        return projectName;
    }

    public String[] getProjectSubject() {
        return projectSubject;
    }
}
