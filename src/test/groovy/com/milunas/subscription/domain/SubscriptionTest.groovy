package com.milunas.subscription.domain

import spock.lang.Specification
import com.milunas.commons.Result

import java.time.LocalDate
import java.time.ZoneId

import static java.time.Duration.ofDays

class SubscriptionTest extends Specification {

    def someDay = LocalDate.of(1989, 12, 15).atStartOfDay(ZoneId.systemDefault()).toInstant()

    def subscriptionStatus = Status.New
    def yetAvailablePauses = 2
    def lastPause = null
    def pauses = new Pauses(yetAvailablePauses, lastPause)
    def subscription = new Subscription(subscriptionStatus, pauses)

    def "should active new sub"() {
        expect:
            subscription.activate() == Result.SUCCESS
    }

    def "should deactivate sub"() {
        given:
            subscription.activate() == Result.SUCCESS
        expect:
            subscription.deactivate() == Result.SUCCESS
    }

    def "should pause activated sub"() {
        given:
            subscription.activate() == Result.SUCCESS
        expect:
            subscription.pause() == Result.SUCCESS
    }

    def "should not pause not active sub"() {
        expect:
            subscription.pause() == Result.FAILURE
    }

    def "should not pause when all pauses used"() {
        given:
            subscription.activate() == Result.SUCCESS
        when:
            assert subscription.pause(someDay.plus(ofDays(10))) == Result.SUCCESS
            assert subscription.resume() == Result.SUCCESS
        and:
            assert subscription.pause(someDay.plus(ofDays(20))) == Result.SUCCESS
            assert subscription.resume() == Result.SUCCESS
        then:
            subscription.pause(someDay.plus(ofDays(30))) == Result.FAILURE
    }

    def "should resume sub"() {
        given:
            subscription.activate()
        and:
            subscription.pause()
        expect:
            subscription.resume() == Result.SUCCESS
    }

    def "should not pause if less than 10 days from last pause"() {
        given:
            subscription.activate() == Result.SUCCESS
        when:
            assert subscription.pause(someDay.plus(ofDays(10))) == Result.SUCCESS
            assert subscription.resume() == Result.SUCCESS
        then:
            subscription.pause(someDay.plus(ofDays(13))) == Result.FAILURE
    }
}
