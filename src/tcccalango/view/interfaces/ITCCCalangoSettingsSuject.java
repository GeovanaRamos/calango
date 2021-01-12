/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tcccalango.view.interfaces;

import tcccalango.util.settings.CalangoSettings;
import tcccalango.view.TCCCalangoView;

/**
 *
 * @author JessicaLuanne
 */
public interface ITCCCalangoSettingsSuject{
    public void adicionarObservador(TCCCalangoView view);
    public void removerObservador(TCCCalangoView view);
    public void notificarObservadores(CalangoSettings novaConfiguracao);
		
}
