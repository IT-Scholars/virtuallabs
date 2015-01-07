
/**
 * ExtensionMapper.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */

            package edu.fiu.cis.acrl.virtuallabs.ws;
            /**
            *  ExtensionMapper class
            */
        
        public  class ExtensionMapper{

          public static java.lang.Object getTypeObject(java.lang.String namespaceURI,
                                                       java.lang.String typeName,
                                                       javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "Course".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.Course.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "TodoType".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.TodoType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "Appointment".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.Appointment.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "UserCredential".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.UserCredential.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "vmInsStatusType".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.VmInsStatusType.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "Action".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.Action.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "Host".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.Host.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "EncryptedCredential".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.EncryptedCredential.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "KServer".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.KServer.Factory.parse(reader);
                        

                  }

              
                  if (
                  "http://acrl.cis.fiu.edu/virtuallabs/ws".equals(namespaceURI) &&
                  "VMInfo".equals(typeName)){
                   
                            return  edu.fiu.cis.acrl.virtuallabs.ws.VMInfo.Factory.parse(reader);
                        

                  }

              
             throw new org.apache.axis2.databinding.ADBException("Unsupported type " + namespaceURI + " " + typeName);
          }

        }
    