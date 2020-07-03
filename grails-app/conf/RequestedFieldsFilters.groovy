import grails.converters.JSON
import org.codehaus.groovy.grails.web.json.JSONObject
import org.grails.plugins.marshallers.config.RequestedFieldsConfigurationHolder

class RequestedFieldsFilters {
    def filters = {
        allCalls(uri: '/**') {
            before = {
                RequestedFieldsConfigurationHolder.clear()
                if (params.fields) {
                    try {
                        def a = new JSONObject(params.fields)
                        a.each { k, v->
                            RequestedFieldsConfigurationHolder.set(k, v)
                        }
                    } catch (Exception e) {
                        RequestedFieldsConfigurationHolder.clear()
                    }
                }
            }
        }
    }
}