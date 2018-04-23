/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.tika.parser.hdf;

//JDK imports
import java.io.InputStream;
//TIKA imports
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.junit.Test;
import org.xml.sax.ContentHandler;
//Junit imports
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * 
 * Test suite for the {@link HDFParser}.
 * 
 */
public class HDFParserTest {
         @Test
     public void testParseGlobalMetadata() throws Exception {
        if(System.getProperty("java.version").startsWith("1.5")) {
            return;
        }
        Parser parser = new HDFParser();
        ContentHandler handler = new BodyContentHandler();
        Metadata metadata = new Metadata();

        /*
         * this is a publicly available HDF5 file from the MLS mission:
         * 
         * 
         * ftp://acdisc.gsfc.nasa.gov/data/s4pa///Aura_MLS_Level2/ML2O3.002//2009
         * /MLS-Aura_L2GP-O3_v02-23-c01_2009d122.he5
         */
        InputStream stream = HDFParser.class
                .getResourceAsStream("/test-documents/test.he5");
        try {
            parser.parse(stream, handler, metadata, new ParseContext());
        } finally {
            stream.close();
        }

        assertNotNull(metadata);
        assertEquals("5", metadata.get("GranuleMonth"));
    }

         @Test
     public void testHDF4() throws Exception {
       if(System.getProperty("java.version").startsWith("1.5")) {
          return;
      }
      Parser parser = new HDFParser();
      ContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();

      /*
       * this is a publicly available HDF4 file from the HD4 examples:
       * 
       * http://www.hdfgroup.org/training/hdf4_chunking/Chunkit/bin/input54kmdata.hdf
       */
      InputStream stream = HDFParser.class
              .getResourceAsStream("/test-documents/test.hdf");
      try {
          parser.parse(stream, handler, metadata, new ParseContext());
      } finally {
          stream.close();
      }

      assertNotNull(metadata);
      assertEquals("Direct read of HDF4 file through CDM library", metadata.get("_History"));
      assertEquals("Ascending", metadata.get("Pass"));
    }
}
