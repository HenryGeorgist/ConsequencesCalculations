/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import org.w3c.dom.Element;

/**
 *
 * @author Will_and_Sara
 */
public interface ISerializeToXMLElement {
    public abstract void ReadFromXMLElement(Element ele);
    public abstract Element WriteToXMLElement();
}
