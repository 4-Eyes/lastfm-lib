//Copyright 2016 Arthur Ghazaryan

//        Licensed under the Apache License, Version 2.0 (the "License");
//        you may not use this file except in compliance with the License.
//        You may obtain a copy of the License at
//
//        http://www.apache.org/licenses/LICENSE-2.0
//
//        Unless required by applicable law or agreed to in writing, software
//        distributed under the License is distributed on an "AS IS" BASIS,
//        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//        See the License for the specific language governing permissions and
//        limitations under the License.


package com.last.fm.api.methods;

import com.last.fm.api.LfmParameters;
import com.last.fm.api.LfmRequest;
import com.last.fm.api.ScrobbleParameters;

import java.util.AbstractMap.SimpleEntry;
import java.util.Collection;
import java.util.Locale;

/**
 * Basic class for all API-requests builders (parts)
 */
public abstract class ApiBase {

    /**
     * Selected methods group
     */
    protected abstract String getMethodsGroup();

    protected LfmRequest prepareRequest(String methodName, LfmParameters methodParameters, boolean needAuth) {
        return new LfmRequest(String.format(Locale.US, "%s.%s", getMethodsGroup(), methodName), methodParameters, needAuth);
    }

    protected LfmRequest prepareRequest(ScrobbleParameters methodParameters) {
        return new LfmRequest(methodParameters);
    }

    @SafeVarargs
    protected final LfmParameters generateParamters(SimpleEntry<String, String>... parameters) {
        LfmParameters params = new LfmParameters();
        for (SimpleEntry<String, String> parameter : parameters) {
            if (parameter.getValue() == null) continue;
            params.put(parameter.getKey(), parameter.getValue());
        }
        return params;
    }

    @SafeVarargs
    protected final ScrobbleParameters generateScrobbleParameters(SimpleEntry<String, Collection<String>>... scrobbleParameters) {
        ScrobbleParameters params = new ScrobbleParameters();
        for (SimpleEntry<String, Collection<String>> parameter : scrobbleParameters) {
            if (parameter.getValue() == null) continue;
            params.put(parameter.getKey(), parameter.getValue().toArray(new String[parameter.getValue().size()]));
        }
        return params;
    }

    protected final Object[] arrayOrNull(Collection arg) {
        if (arg == null) {
            return null;
        }
        return arg.toArray();
    }
}
