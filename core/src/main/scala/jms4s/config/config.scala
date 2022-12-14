/*
 * Copyright (c) 2020 Functional Programming in Bologna
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of
 * this software and associated documentation files (the "Software"), to deal in
 * the Software without restriction, including without limitation the rights to
 * use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
 * the Software, and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS
 * FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER
 * IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN
 * CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */

package jms4s.config

import cats.Order

import java.util.UUID

sealed trait Destination

sealed trait TemporaryDestination extends Destination

case class TemporaryQueue(value: UUID = java.util.UUID.randomUUID()) extends TemporaryDestination {
  override def toString: String = s"temporary-queue#$value"
}

case class TemporaryTopic(value: UUID = java.util.UUID.randomUUID()) extends TemporaryDestination {
  override def toString: String = s"temporary-topic#$value"
}

sealed trait DestinationName        extends Destination with Product with Serializable
case class QueueName(value: String) extends DestinationName
case class TopicName(value: String) extends DestinationName

object DestinationName {

  implicit val orderingDestinationName: Order[DestinationName] = Order.from[DestinationName] {
    case (x, y) => Order[String].compare(x.toString, y.toString)
  }
}
