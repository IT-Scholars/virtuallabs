
/**
 * Appointment.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.5  Built on : Apr 30, 2009 (06:07:47 EDT)
 */
            
                package edu.fiu.cis.acrl.virtuallabs.ws;
            

            /**
            *  Appointment bean class
            */
        
        public  class Appointment
        implements org.apache.axis2.databinding.ADBBean{
        /* This type was generated from the piece of schema that had
                name = Appointment
                Namespace URI = http://acrl.cis.fiu.edu/virtuallabs/ws
                Namespace Prefix = ns1
                */
            

        private static java.lang.String generatePrefix(java.lang.String namespace) {
            if(namespace.equals("http://acrl.cis.fiu.edu/virtuallabs/ws")){
                return "ns1";
            }
            return org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
        }

        

                        /**
                        * field for Id
                        */

                        
                                    protected java.lang.String localId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getId(){
                               return localId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Id
                               */
                               public void setId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localIdTracker = true;
                                       } else {
                                          localIdTracker = false;
                                              
                                       }
                                   
                                            this.localId=param;
                                    

                               }
                            

                        /**
                        * field for UserName
                        */

                        
                                    protected java.lang.String localUserName ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localUserNameTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getUserName(){
                               return localUserName;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param UserName
                               */
                               public void setUserName(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localUserNameTracker = true;
                                       } else {
                                          localUserNameTracker = false;
                                              
                                       }
                                   
                                            this.localUserName=param;
                                    

                               }
                            

                        /**
                        * field for Start
                        */

                        
                                    protected java.util.Calendar localStart ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Calendar
                           */
                           public  java.util.Calendar getStart(){
                               return localStart;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Start
                               */
                               public void setStart(java.util.Calendar param){
                            
                                            this.localStart=param;
                                    

                               }
                            

                        /**
                        * field for End
                        */

                        
                                    protected java.util.Calendar localEnd ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.util.Calendar
                           */
                           public  java.util.Calendar getEnd(){
                               return localEnd;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param End
                               */
                               public void setEnd(java.util.Calendar param){
                            
                                            this.localEnd=param;
                                    

                               }
                            

                        /**
                        * field for ResourceType
                        */

                        
                                    protected java.lang.String localResourceType ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getResourceType(){
                               return localResourceType;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param ResourceType
                               */
                               public void setResourceType(java.lang.String param){
                            
                                            this.localResourceType=param;
                                    

                               }
                            

                        /**
                        * field for Course
                        */

                        
                                    protected java.lang.String localCourse ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localCourseTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getCourse(){
                               return localCourse;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param Course
                               */
                               public void setCourse(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localCourseTracker = true;
                                       } else {
                                          localCourseTracker = false;
                                              
                                       }
                                   
                                            this.localCourse=param;
                                    

                               }
                            

                        /**
                        * field for AffiliationId
                        */

                        
                                    protected java.lang.String localAffiliationId ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localAffiliationIdTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAffiliationId(){
                               return localAffiliationId;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AffiliationId
                               */
                               public void setAffiliationId(java.lang.String param){
                            
                                       if (param != null){
                                          //update the setting tracker
                                          localAffiliationIdTracker = true;
                                       } else {
                                          localAffiliationIdTracker = false;
                                              
                                       }
                                   
                                            this.localAffiliationId=param;
                                    

                               }
                            

                        /**
                        * field for AvailabilityStatus
                        */

                        
                                    protected java.lang.String localAvailabilityStatus ;
                                

                           /**
                           * Auto generated getter method
                           * @return java.lang.String
                           */
                           public  java.lang.String getAvailabilityStatus(){
                               return localAvailabilityStatus;
                           }

                           
                        
                            /**
                               * Auto generated setter method
                               * @param param AvailabilityStatus
                               */
                               public void setAvailabilityStatus(java.lang.String param){
                            
                                            this.localAvailabilityStatus=param;
                                    

                               }
                            

                        /**
                        * field for Action
                        * This was an Array!
                        */

                        
                                    protected edu.fiu.cis.acrl.virtuallabs.ws.Action[] localAction ;
                                
                           /*  This tracker boolean wil be used to detect whether the user called the set method
                          *   for this attribute. It will be used to determine whether to include this field
                           *   in the serialized XML
                           */
                           protected boolean localActionTracker = false ;
                           

                           /**
                           * Auto generated getter method
                           * @return edu.fiu.cis.acrl.virtuallabs.ws.Action[]
                           */
                           public  edu.fiu.cis.acrl.virtuallabs.ws.Action[] getAction(){
                               return localAction;
                           }

                           
                        


                               
                              /**
                               * validate the array for Action
                               */
                              protected void validateAction(edu.fiu.cis.acrl.virtuallabs.ws.Action[] param){
                             
                              }


                             /**
                              * Auto generated setter method
                              * @param param Action
                              */
                              public void setAction(edu.fiu.cis.acrl.virtuallabs.ws.Action[] param){
                              
                                   validateAction(param);

                               
                                          if (param != null){
                                             //update the setting tracker
                                             localActionTracker = true;
                                          } else {
                                             localActionTracker = false;
                                                 
                                          }
                                      
                                      this.localAction=param;
                              }

                               
                             
                             /**
                             * Auto generated add method for the array for convenience
                             * @param param edu.fiu.cis.acrl.virtuallabs.ws.Action
                             */
                             public void addAction(edu.fiu.cis.acrl.virtuallabs.ws.Action param){
                                   if (localAction == null){
                                   localAction = new edu.fiu.cis.acrl.virtuallabs.ws.Action[]{};
                                   }

                            
                                 //update the setting tracker
                                localActionTracker = true;
                            

                               java.util.List list =
                            org.apache.axis2.databinding.utils.ConverterUtil.toList(localAction);
                               list.add(param);
                               this.localAction =
                             (edu.fiu.cis.acrl.virtuallabs.ws.Action[])list.toArray(
                            new edu.fiu.cis.acrl.virtuallabs.ws.Action[list.size()]);

                             }
                             

     /**
     * isReaderMTOMAware
     * @return true if the reader supports MTOM
     */
   public static boolean isReaderMTOMAware(javax.xml.stream.XMLStreamReader reader) {
        boolean isReaderMTOMAware = false;
        
        try{
          isReaderMTOMAware = java.lang.Boolean.TRUE.equals(reader.getProperty(org.apache.axiom.om.OMConstants.IS_DATA_HANDLERS_AWARE));
        }catch(java.lang.IllegalArgumentException e){
          isReaderMTOMAware = false;
        }
        return isReaderMTOMAware;
   }
     
     
        /**
        *
        * @param parentQName
        * @param factory
        * @return org.apache.axiom.om.OMElement
        */
       public org.apache.axiom.om.OMElement getOMElement (
               final javax.xml.namespace.QName parentQName,
               final org.apache.axiom.om.OMFactory factory) throws org.apache.axis2.databinding.ADBException{


        
               org.apache.axiom.om.OMDataSource dataSource =
                       new org.apache.axis2.databinding.ADBDataSource(this,parentQName){

                 public void serialize(org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
                       Appointment.this.serialize(parentQName,factory,xmlWriter);
                 }
               };
               return new org.apache.axiom.om.impl.llom.OMSourcedElementImpl(
               parentQName,factory,dataSource);
            
       }

         public void serialize(final javax.xml.namespace.QName parentQName,
                                       final org.apache.axiom.om.OMFactory factory,
                                       org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter)
                                throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
                           serialize(parentQName,factory,xmlWriter,false);
         }

         public void serialize(final javax.xml.namespace.QName parentQName,
                               final org.apache.axiom.om.OMFactory factory,
                               org.apache.axis2.databinding.utils.writer.MTOMAwareXMLStreamWriter xmlWriter,
                               boolean serializeType)
            throws javax.xml.stream.XMLStreamException, org.apache.axis2.databinding.ADBException{
            
                


                java.lang.String prefix = null;
                java.lang.String namespace = null;
                

                    prefix = parentQName.getPrefix();
                    namespace = parentQName.getNamespaceURI();

                    if ((namespace != null) && (namespace.trim().length() > 0)) {
                        java.lang.String writerPrefix = xmlWriter.getPrefix(namespace);
                        if (writerPrefix != null) {
                            xmlWriter.writeStartElement(namespace, parentQName.getLocalPart());
                        } else {
                            if (prefix == null) {
                                prefix = generatePrefix(namespace);
                            }

                            xmlWriter.writeStartElement(prefix, parentQName.getLocalPart(), namespace);
                            xmlWriter.writeNamespace(prefix, namespace);
                            xmlWriter.setPrefix(prefix, namespace);
                        }
                    } else {
                        xmlWriter.writeStartElement(parentQName.getLocalPart());
                    }
                
                  if (serializeType){
               

                   java.lang.String namespacePrefix = registerPrefix(xmlWriter,"http://acrl.cis.fiu.edu/virtuallabs/ws");
                   if ((namespacePrefix != null) && (namespacePrefix.trim().length() > 0)){
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           namespacePrefix+":Appointment",
                           xmlWriter);
                   } else {
                       writeAttribute("xsi","http://www.w3.org/2001/XMLSchema-instance","type",
                           "Appointment",
                           xmlWriter);
                   }

               
                   }
                if (localIdTracker){
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"id", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"id");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("id");
                                    }
                                

                                          if (localId==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("id cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localUserNameTracker){
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"userName", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"userName");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("userName");
                                    }
                                

                                          if (localUserName==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("userName cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localUserName);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"start", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"start");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("start");
                                    }
                                

                                          if (localStart==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("start cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStart));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"end", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"end");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("end");
                                    }
                                

                                          if (localEnd==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("end cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnd));
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"resourceType", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"resourceType");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("resourceType");
                                    }
                                

                                          if (localResourceType==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("resourceType cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localResourceType);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localCourseTracker){
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"course", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"course");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("course");
                                    }
                                

                                          if (localCourse==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("course cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localCourse);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             } if (localAffiliationIdTracker){
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"affiliationId", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"affiliationId");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("affiliationId");
                                    }
                                

                                          if (localAffiliationId==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("affiliationId cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAffiliationId);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                             }
                                    namespace = "http://acrl.cis.fiu.edu/virtuallabs/ws";
                                    if (! namespace.equals("")) {
                                        prefix = xmlWriter.getPrefix(namespace);

                                        if (prefix == null) {
                                            prefix = generatePrefix(namespace);

                                            xmlWriter.writeStartElement(prefix,"availabilityStatus", namespace);
                                            xmlWriter.writeNamespace(prefix, namespace);
                                            xmlWriter.setPrefix(prefix, namespace);

                                        } else {
                                            xmlWriter.writeStartElement(namespace,"availabilityStatus");
                                        }

                                    } else {
                                        xmlWriter.writeStartElement("availabilityStatus");
                                    }
                                

                                          if (localAvailabilityStatus==null){
                                              // write the nil attribute
                                              
                                                     throw new org.apache.axis2.databinding.ADBException("availabilityStatus cannot be null!!");
                                                  
                                          }else{

                                        
                                                   xmlWriter.writeCharacters(localAvailabilityStatus);
                                            
                                          }
                                    
                                   xmlWriter.writeEndElement();
                              if (localActionTracker){
                                       if (localAction!=null){
                                            for (int i = 0;i < localAction.length;i++){
                                                if (localAction[i] != null){
                                                 localAction[i].serialize(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","action"),
                                                           factory,xmlWriter);
                                                } else {
                                                   
                                                        // we don't have to do any thing since minOccures is zero
                                                    
                                                }

                                            }
                                     } else {
                                        
                                               throw new org.apache.axis2.databinding.ADBException("action cannot be null!!");
                                        
                                    }
                                 }
                    xmlWriter.writeEndElement();
               

        }

         /**
          * Util method to write an attribute with the ns prefix
          */
          private void writeAttribute(java.lang.String prefix,java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
              if (xmlWriter.getPrefix(namespace) == null) {
                       xmlWriter.writeNamespace(prefix, namespace);
                       xmlWriter.setPrefix(prefix, namespace);

              }

              xmlWriter.writeAttribute(namespace,attName,attValue);

         }

        /**
          * Util method to write an attribute without the ns prefix
          */
          private void writeAttribute(java.lang.String namespace,java.lang.String attName,
                                      java.lang.String attValue,javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException{
                if (namespace.equals(""))
              {
                  xmlWriter.writeAttribute(attName,attValue);
              }
              else
              {
                  registerPrefix(xmlWriter, namespace);
                  xmlWriter.writeAttribute(namespace,attName,attValue);
              }
          }


           /**
             * Util method to write an attribute without the ns prefix
             */
            private void writeQNameAttribute(java.lang.String namespace, java.lang.String attName,
                                             javax.xml.namespace.QName qname, javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

                java.lang.String attributeNamespace = qname.getNamespaceURI();
                java.lang.String attributePrefix = xmlWriter.getPrefix(attributeNamespace);
                if (attributePrefix == null) {
                    attributePrefix = registerPrefix(xmlWriter, attributeNamespace);
                }
                java.lang.String attributeValue;
                if (attributePrefix.trim().length() > 0) {
                    attributeValue = attributePrefix + ":" + qname.getLocalPart();
                } else {
                    attributeValue = qname.getLocalPart();
                }

                if (namespace.equals("")) {
                    xmlWriter.writeAttribute(attName, attributeValue);
                } else {
                    registerPrefix(xmlWriter, namespace);
                    xmlWriter.writeAttribute(namespace, attName, attributeValue);
                }
            }
        /**
         *  method to handle Qnames
         */

        private void writeQName(javax.xml.namespace.QName qname,
                                javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {
            java.lang.String namespaceURI = qname.getNamespaceURI();
            if (namespaceURI != null) {
                java.lang.String prefix = xmlWriter.getPrefix(namespaceURI);
                if (prefix == null) {
                    prefix = generatePrefix(namespaceURI);
                    xmlWriter.writeNamespace(prefix, namespaceURI);
                    xmlWriter.setPrefix(prefix,namespaceURI);
                }

                if (prefix.trim().length() > 0){
                    xmlWriter.writeCharacters(prefix + ":" + org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                } else {
                    // i.e this is the default namespace
                    xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
                }

            } else {
                xmlWriter.writeCharacters(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qname));
            }
        }

        private void writeQNames(javax.xml.namespace.QName[] qnames,
                                 javax.xml.stream.XMLStreamWriter xmlWriter) throws javax.xml.stream.XMLStreamException {

            if (qnames != null) {
                // we have to store this data until last moment since it is not possible to write any
                // namespace data after writing the charactor data
                java.lang.StringBuffer stringToWrite = new java.lang.StringBuffer();
                java.lang.String namespaceURI = null;
                java.lang.String prefix = null;

                for (int i = 0; i < qnames.length; i++) {
                    if (i > 0) {
                        stringToWrite.append(" ");
                    }
                    namespaceURI = qnames[i].getNamespaceURI();
                    if (namespaceURI != null) {
                        prefix = xmlWriter.getPrefix(namespaceURI);
                        if ((prefix == null) || (prefix.length() == 0)) {
                            prefix = generatePrefix(namespaceURI);
                            xmlWriter.writeNamespace(prefix, namespaceURI);
                            xmlWriter.setPrefix(prefix,namespaceURI);
                        }

                        if (prefix.trim().length() > 0){
                            stringToWrite.append(prefix).append(":").append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        } else {
                            stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                        }
                    } else {
                        stringToWrite.append(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(qnames[i]));
                    }
                }
                xmlWriter.writeCharacters(stringToWrite.toString());
            }

        }


         /**
         * Register a namespace prefix
         */
         private java.lang.String registerPrefix(javax.xml.stream.XMLStreamWriter xmlWriter, java.lang.String namespace) throws javax.xml.stream.XMLStreamException {
                java.lang.String prefix = xmlWriter.getPrefix(namespace);

                if (prefix == null) {
                    prefix = generatePrefix(namespace);

                    while (xmlWriter.getNamespaceContext().getNamespaceURI(prefix) != null) {
                        prefix = org.apache.axis2.databinding.utils.BeanUtil.getUniquePrefix();
                    }

                    xmlWriter.writeNamespace(prefix, namespace);
                    xmlWriter.setPrefix(prefix, namespace);
                }

                return prefix;
            }


  
        /**
        * databinding method to get an XML representation of this object
        *
        */
        public javax.xml.stream.XMLStreamReader getPullParser(javax.xml.namespace.QName qName)
                    throws org.apache.axis2.databinding.ADBException{


        
                 java.util.ArrayList elementList = new java.util.ArrayList();
                 java.util.ArrayList attribList = new java.util.ArrayList();

                 if (localIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "id"));
                                 
                                        if (localId != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localId));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("id cannot be null!!");
                                        }
                                    } if (localUserNameTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "userName"));
                                 
                                        if (localUserName != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localUserName));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("userName cannot be null!!");
                                        }
                                    }
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "start"));
                                 
                                        if (localStart != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localStart));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("start cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "end"));
                                 
                                        if (localEnd != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localEnd));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("end cannot be null!!");
                                        }
                                    
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "resourceType"));
                                 
                                        if (localResourceType != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localResourceType));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("resourceType cannot be null!!");
                                        }
                                     if (localCourseTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "course"));
                                 
                                        if (localCourse != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localCourse));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("course cannot be null!!");
                                        }
                                    } if (localAffiliationIdTracker){
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "affiliationId"));
                                 
                                        if (localAffiliationId != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAffiliationId));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("affiliationId cannot be null!!");
                                        }
                                    }
                                      elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                      "availabilityStatus"));
                                 
                                        if (localAvailabilityStatus != null){
                                            elementList.add(org.apache.axis2.databinding.utils.ConverterUtil.convertToString(localAvailabilityStatus));
                                        } else {
                                           throw new org.apache.axis2.databinding.ADBException("availabilityStatus cannot be null!!");
                                        }
                                     if (localActionTracker){
                             if (localAction!=null) {
                                 for (int i = 0;i < localAction.length;i++){

                                    if (localAction[i] != null){
                                         elementList.add(new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws",
                                                                          "action"));
                                         elementList.add(localAction[i]);
                                    } else {
                                        
                                                // nothing to do
                                            
                                    }

                                 }
                             } else {
                                 
                                        throw new org.apache.axis2.databinding.ADBException("action cannot be null!!");
                                    
                             }

                        }

                return new org.apache.axis2.databinding.utils.reader.ADBXMLStreamReaderImpl(qName, elementList.toArray(), attribList.toArray());
            
            

        }

  

     /**
      *  Factory class that keeps the parse method
      */
    public static class Factory{

        
        

        /**
        * static method to create the object
        * Precondition:  If this object is an element, the current or next start element starts this object and any intervening reader events are ignorable
        *                If this object is not an element, it is a complex type and the reader is at the event just after the outer start element
        * Postcondition: If this object is an element, the reader is positioned at its end element
        *                If this object is a complex type, the reader is positioned at the end element of its outer element
        */
        public static Appointment parse(javax.xml.stream.XMLStreamReader reader) throws java.lang.Exception{
            Appointment object =
                new Appointment();

            int event;
            java.lang.String nillableValue = null;
            java.lang.String prefix ="";
            java.lang.String namespaceuri ="";
            try {
                
                while (!reader.isStartElement() && !reader.isEndElement())
                    reader.next();

                
                if (reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance","type")!=null){
                  java.lang.String fullTypeName = reader.getAttributeValue("http://www.w3.org/2001/XMLSchema-instance",
                        "type");
                  if (fullTypeName!=null){
                    java.lang.String nsPrefix = null;
                    if (fullTypeName.indexOf(":") > -1){
                        nsPrefix = fullTypeName.substring(0,fullTypeName.indexOf(":"));
                    }
                    nsPrefix = nsPrefix==null?"":nsPrefix;

                    java.lang.String type = fullTypeName.substring(fullTypeName.indexOf(":")+1);
                    
                            if (!"Appointment".equals(type)){
                                //find namespace for the prefix
                                java.lang.String nsUri = reader.getNamespaceContext().getNamespaceURI(nsPrefix);
                                return (Appointment)edu.fiu.cis.acrl.virtuallabs.ws.ExtensionMapper.getTypeObject(
                                     nsUri,type,reader);
                              }
                        

                  }
                

                }

                

                
                // Note all attributes that were handled. Used to differ normal attributes
                // from anyAttributes.
                java.util.Vector handledAttributes = new java.util.Vector();
                

                 
                    
                    reader.next();
                
                        java.util.ArrayList list9 = new java.util.ArrayList();
                    
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","id").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","userName").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setUserName(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","start").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setStart(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","end").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setEnd(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToDateTime(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","resourceType").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setResourceType(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","course").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setCourse(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","affiliationId").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAffiliationId(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","availabilityStatus").equals(reader.getName())){
                                
                                    java.lang.String content = reader.getElementText();
                                    
                                              object.setAvailabilityStatus(
                                                    org.apache.axis2.databinding.utils.ConverterUtil.convertToString(content));
                                              
                                        reader.next();
                                    
                              }  // End of if for expected property start element
                                
                                else{
                                    // A start element we are not expecting indicates an invalid parameter was passed
                                    throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                                }
                            
                                    
                                    while (!reader.isStartElement() && !reader.isEndElement()) reader.next();
                                
                                    if (reader.isStartElement() && new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","action").equals(reader.getName())){
                                
                                    
                                    
                                    // Process the array and step past its final element's end.
                                    list9.add(edu.fiu.cis.acrl.virtuallabs.ws.Action.Factory.parse(reader));
                                                                
                                                        //loop until we find a start element that is not part of this array
                                                        boolean loopDone9 = false;
                                                        while(!loopDone9){
                                                            // We should be at the end element, but make sure
                                                            while (!reader.isEndElement())
                                                                reader.next();
                                                            // Step out of this element
                                                            reader.next();
                                                            // Step to next element event.
                                                            while (!reader.isStartElement() && !reader.isEndElement())
                                                                reader.next();
                                                            if (reader.isEndElement()){
                                                                //two continuous end elements means we are exiting the xml structure
                                                                loopDone9 = true;
                                                            } else {
                                                                if (new javax.xml.namespace.QName("http://acrl.cis.fiu.edu/virtuallabs/ws","action").equals(reader.getName())){
                                                                    list9.add(edu.fiu.cis.acrl.virtuallabs.ws.Action.Factory.parse(reader));
                                                                        
                                                                }else{
                                                                    loopDone9 = true;
                                                                }
                                                            }
                                                        }
                                                        // call the converter utility  to convert and set the array
                                                        
                                                        object.setAction((edu.fiu.cis.acrl.virtuallabs.ws.Action[])
                                                            org.apache.axis2.databinding.utils.ConverterUtil.convertToArray(
                                                                edu.fiu.cis.acrl.virtuallabs.ws.Action.class,
                                                                list9));
                                                            
                              }  // End of if for expected property start element
                                
                                    else {
                                        
                                    }
                                  
                            while (!reader.isStartElement() && !reader.isEndElement())
                                reader.next();
                            
                                if (reader.isStartElement())
                                // A start element we are not expecting indicates a trailing invalid property
                                throw new org.apache.axis2.databinding.ADBException("Unexpected subelement " + reader.getLocalName());
                            



            } catch (javax.xml.stream.XMLStreamException e) {
                throw new java.lang.Exception(e);
            }

            return object;
        }

        }//end of factory class

        

        }
           
          