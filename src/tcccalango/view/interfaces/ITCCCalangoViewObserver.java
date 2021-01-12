/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.interfaces;

import tcccalango.util.settings.CalangoSettings;

/**
 *
 * @author JessicaLuanne
 */
public interface ITCCCalangoViewObserver {
    public void atualizar(CalangoSettings novaConfiguracao);
}
