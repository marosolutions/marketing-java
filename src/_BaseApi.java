import utils.StringUtils;

public abstract class _BaseApi {
    protected int _accountId;
    protected String _authToken;
    protected String _urlPathRoot;
    protected String _baseUrl;

    
    public _BaseApi(int accountId, String authToken, String urlPathRoot, String baseUrl) throws IllegalArgumentException {
        if (authToken == null || authToken == "") {
            throw new IllegalArgumentException("authToken must be provided.");
        }
        if (accountId <= 0) {
            throw new IllegalArgumentException("accountId must be greater than 0.");
        }
        _accountId = accountId;
        _authToken = authToken;
        _urlPathRoot = urlPathRoot;
        _baseUrl = baseUrl;
    }

    private String getUrl(String resource, String overrideResource)
    {
        String url = String.format((StringUtils.isNullOrEmpty(_baseUrl) ? "https://api.maropost.com/accounts/%1/" : _baseUrl), _accountId);
        if (StringUtils.isNullOrEmpty(overrideResource))
        {
            url += StringUtils.isNullOrEmpty(_urlPathRoot) ? "" : _urlPathRoot;
            url += StringUtils.isNullOrEmpty(resource) ? "" : ("/" + resource);
        }
        else
        {
            url += overrideResource;
            url += StringUtils.isNullOrEmpty(resource) ? "" : ("/" + resource);
        }
        return url;
    }

    private String getQueryString(IEnumerable<KeyValuePair<String, object>> keyValuePairs)
    {
        String queryStr = StringUtils.isNullOrEmpty(_authToken) ? "" : ("?auth_token=" + _authToken);
        if (keyValuePairs != null)
        {
            foreach (var keyValuePair in keyValuePairs)
            {
                queryStr += $"&{keyValuePair.Key}={keyValuePair.Value}";
            }
        }
        queryStr = queryStr.replace(' ', '+');
        return queryStr;
    }

    protected async Task<IOperationResult<dynamic>> get(String resource, IEnumerable<KeyValuePair<String, object>> queryStringParams = null, String overrideUrlPathRoot = null)
    {
        var url = $"{GetUrl(resource, overrideUrlPathRoot)}.json{GetQueryString(queryStringParams)}";
        var request = new HttpRequestMessage(HttpMethod.Get, url);
        dynamic responseBody;
        request.Headers.Accept.Clear();
        request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        HttpResponseMessage apiResponse;
        try
        {
            apiResponse = await HttpClient.SendAsync(request);
        }
        catch (HttpRequestException e)
        {
            return new OperationResult<dynamic>(null, e);
        }
        var data = apiResponse.Content.ReadAsStringAsync().Result;
        responseBody = Newtonsoft.Json.JsonConvert.DeserializeObject(data);
        return new OperationResult<dynamic>(responseBody, apiResponse, "");
    }

    protected async Task<IOperationResult<dynamic>> post(String resource, IEnumerable<KeyValuePair<String, object>> queryStringParams = null, object obj = null, String overrideUrlPathRoot = null)
    {
        dynamic responseBody = null;
        var url = $"{GetUrl(resource, overrideUrlPathRoot)}.json{GetQueryString(queryStringParams)}";
        var request = new HttpRequestMessage(HttpMethod.Post, url);
        request.Headers.Accept.Clear();
        request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        var jsonContent = Newtonsoft.Json.JsonConvert.SerializeObject(obj);
        request.Content = new StringContent(jsonContent, Encoding.UTF8, "application/json");
        HttpResponseMessage apiResponse;
        try
        {
            apiResponse = await HttpClient.SendAsync(request);
        }
        catch (HttpRequestException e)
        {
            return new OperationResult<dynamic>(null, e);
        }
        var data = apiResponse.Content.ReadAsStringAsync().Result;
        responseBody = Newtonsoft.Json.JsonConvert.DeserializeObject(data);
        return new OperationResult<dynamic>(responseBody, apiResponse, "");
    }

    protected async Task<IOperationResult<dynamic>> put(String resource, IEnumerable<KeyValuePair<String, object>> queryStringParams = null, object obj = null, String overrideUrlPathRoot = null)
    {
        dynamic responseBody = null;
        var url = $"{GetUrl(resource, overrideUrlPathRoot)}.json{GetQueryString(queryStringParams)}";
        var request = new HttpRequestMessage(HttpMethod.Put, url);
        request.Headers.Accept.Clear();
        request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        var jsonContent = Newtonsoft.Json.JsonConvert.SerializeObject(obj);
        request.Content = new StringContent(jsonContent, Encoding.UTF8, "application/json");
        HttpResponseMessage apiResponse;
        try
        {
            apiResponse = await HttpClient.SendAsync(request);
        }
        catch (HttpRequestException e)
        {
            return new OperationResult<dynamic>(null, e);
        }
        var data = apiResponse.Content.ReadAsStringAsync().Result;
        try { responseBody = Newtonsoft.Json.JsonConvert.DeserializeObject(data); }
        catch { responseBody = data; }
        return new OperationResult<dynamic>(responseBody, apiResponse, "");
    }

    protected async Task<IOperationResult<dynamic>> delete(String resource, IEnumerable<KeyValuePair<String, object>> queryStringParams = null, object obj = null, String overrideUrlPathRoot = null)
    {
        dynamic responseBody = null;
        var url = $"{GetUrl(resource, overrideUrlPathRoot)}.json{GetQueryString(queryStringParams)}";
        var request = new HttpRequestMessage(HttpMethod.Delete, url);
        request.Headers.Accept.Clear();
        request.Headers.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
        var jsonContent = Newtonsoft.Json.JsonConvert.SerializeObject(obj);
        request.Content = new StringContent(jsonContent, Encoding.UTF8, "application/json");
        HttpResponseMessage apiResponse;
        try
        {
            apiResponse = await HttpClient.SendAsync(request);
        }
        catch (HttpRequestException e)
        {
            return new OperationResult<dynamic>(null, e);
        }
        var data = apiResponse.Content.ReadAsStringAsync().Result;
        try { responseBody = Newtonsoft.Json.JsonConvert.DeserializeObject(data); }
        catch { responseBody = data; }
        return new OperationResult<dynamic>(responseBody, apiResponse, "");
    }
}