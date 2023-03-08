package com.example.academysession1.service.client;

import java.io.IOException;
import java.util.HashMap;

public interface SearchEngineClient {

    HashMap<String, String> getClient(String query) throws IOException;
}
