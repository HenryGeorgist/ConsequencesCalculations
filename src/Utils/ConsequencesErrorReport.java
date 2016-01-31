/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.ArrayList;

/**
 *
 * @author Will_and_Sara
 */
public class ConsequencesErrorReport {
    private ArrayList<String> _Errors;
    public ConsequencesErrorReport(){
        _Errors = new ArrayList<>();
    }
    public void AddErrorMessage(String ErrorMessage){
        _Errors.add(ErrorMessage);
    }
}
