package br.com.icb.javafx.cell.factory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.util.Callback;

public class SimplePropertyValueFactory<S, T> implements Callback<CellDataFeatures<S, T>, ObservableValue<T>> {

	private final String property;

	public SimplePropertyValueFactory(String property) {
		this.property = property;
	}

	public String getProperty() {
		return this.property;
	}

	@Override
	public ObservableValue<T> call(CellDataFeatures<S, T> param) {
		return getCellDataReflectively(param.getValue());
	}

	@SuppressWarnings("unchecked")
	private ObservableValue<T> getCellDataReflectively(S cellData) {

		try {
			BeanInfo beanInfo = Introspector.getBeanInfo(cellData.getClass());
			PropertyDescriptor[] descriptors = beanInfo.getPropertyDescriptors();

			for (PropertyDescriptor propertyDescriptor : descriptors) {
				if (propertyDescriptor.getDisplayName().equals(this.property)) {
					return (ObservableValue<T>) propertyDescriptor.getReadMethod().invoke(cellData, (Object[]) null);
				}
			}
		} catch (IntrospectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
