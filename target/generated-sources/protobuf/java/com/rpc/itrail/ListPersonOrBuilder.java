// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: person-service.proto

package com.rpc.itrail;

public interface ListPersonOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ListPerson)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <code>repeated .PersonResponse personResponse = 5;</code>
   */
  java.util.List<com.rpc.itrail.PersonResponse> 
      getPersonResponseList();
  /**
   * <code>repeated .PersonResponse personResponse = 5;</code>
   */
  com.rpc.itrail.PersonResponse getPersonResponse(int index);
  /**
   * <code>repeated .PersonResponse personResponse = 5;</code>
   */
  int getPersonResponseCount();
  /**
   * <code>repeated .PersonResponse personResponse = 5;</code>
   */
  java.util.List<? extends com.rpc.itrail.PersonResponseOrBuilder> 
      getPersonResponseOrBuilderList();
  /**
   * <code>repeated .PersonResponse personResponse = 5;</code>
   */
  com.rpc.itrail.PersonResponseOrBuilder getPersonResponseOrBuilder(
      int index);
}