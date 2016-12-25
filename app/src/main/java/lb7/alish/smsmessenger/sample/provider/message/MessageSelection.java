/*
 * This source is part of the
 *      _____  ___   ____
 *  __ / / _ \/ _ | / __/___  _______ _
 * / // / , _/ __ |/ _/_/ _ \/ __/ _ `/
 * \___/_/|_/_/ |_/_/ (_)___/_/  \_, /
 *                              /___/
 * repository.
 *
 * Copyright (C) 2012-2015 Benoit 'BoD' Lubek (BoD@JRAF.org)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package lb7.alish.smsmessenger.sample.provider.message;

// @formatter:off

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import java.util.Date;

import lb7.alish.smsmessenger.sample.provider.base.AbstractSelection;

/**
 * Selection for the {@code message} table.
 */
@SuppressWarnings({"unused", "WeakerAccess", "Recycle"})
public class MessageSelection extends AbstractSelection<MessageSelection> {
    @Override
    protected Uri baseUri() {
        return MessageColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection      A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MessageCursor} object, which is positioned before the first entry, or null.
     */
    public MessageCursor query(ContentResolver contentResolver, String[] projection) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MessageCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, null)}.
     */
    public MessageCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null);
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param context    The context to use for the query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @return A {@code MessageCursor} object, which is positioned before the first entry, or null.
     */
    public MessageCursor query(Context context, String[] projection) {
        Cursor cursor = context.getContentResolver().query(uri(), projection, sel(), args(), order());
        if (cursor == null) return null;
        return new MessageCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(context, null)}.
     */
    public MessageCursor query(Context context) {
        return query(context, null);
    }


    public MessageSelection id(long... value) {
        addEquals("message." + MessageColumns._ID, toObjectArray(value));
        return this;
    }

    public MessageSelection idNot(long... value) {
        addNotEquals("message." + MessageColumns._ID, toObjectArray(value));
        return this;
    }

    public MessageSelection orderById(boolean desc) {
        orderBy("message." + MessageColumns._ID, desc);
        return this;
    }

    public MessageSelection orderById() {
        return orderById(false);
    }

    public MessageSelection number(String... value) {
        addEquals(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection numberNot(String... value) {
        addNotEquals(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection numberLike(String... value) {
        addLike(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection numberContains(String... value) {
        addContains(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection numberStartsWith(String... value) {
        addStartsWith(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection numberEndsWith(String... value) {
        addEndsWith(MessageColumns.NUMBER, value);
        return this;
    }

    public MessageSelection orderByNumber(boolean desc) {
        orderBy(MessageColumns.NUMBER, desc);
        return this;
    }

    public MessageSelection orderByNumber() {
        orderBy(MessageColumns.NUMBER, false);
        return this;
    }

    public MessageSelection messageText(String... value) {
        addEquals(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection messageTextNot(String... value) {
        addNotEquals(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection messageTextLike(String... value) {
        addLike(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection messageTextContains(String... value) {
        addContains(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection messageTextStartsWith(String... value) {
        addStartsWith(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection messageTextEndsWith(String... value) {
        addEndsWith(MessageColumns.MESSAGE_TEXT, value);
        return this;
    }

    public MessageSelection orderByMessageText(boolean desc) {
        orderBy(MessageColumns.MESSAGE_TEXT, desc);
        return this;
    }

    public MessageSelection orderByMessageText() {
        orderBy(MessageColumns.MESSAGE_TEXT, false);
        return this;
    }

    public MessageSelection date(Date... value) {
        addEquals(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection dateNot(Date... value) {
        addNotEquals(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection date(Long... value) {
        addEquals(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection dateAfter(Date value) {
        addGreaterThan(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection dateAfterEq(Date value) {
        addGreaterThanOrEquals(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection dateBefore(Date value) {
        addLessThan(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection dateBeforeEq(Date value) {
        addLessThanOrEquals(MessageColumns.DATE, value);
        return this;
    }

    public MessageSelection orderByDate(boolean desc) {
        orderBy(MessageColumns.DATE, desc);
        return this;
    }

    public MessageSelection orderByDate() {
        orderBy(MessageColumns.DATE, false);
        return this;
    }

    public MessageSelection type(DirectionType... value) {
        addEquals(MessageColumns.TYPE, value);
        return this;
    }

    public MessageSelection typeNot(DirectionType... value) {
        addNotEquals(MessageColumns.TYPE, value);
        return this;
    }


    public MessageSelection orderByType(boolean desc) {
        orderBy(MessageColumns.TYPE, desc);
        return this;
    }

    public MessageSelection orderByType() {
        orderBy(MessageColumns.TYPE, false);
        return this;
    }

    public MessageSelection status(String... value) {
        addEquals(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection statusNot(String... value) {
        addNotEquals(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection statusLike(String... value) {
        addLike(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection statusContains(String... value) {
        addContains(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection statusStartsWith(String... value) {
        addStartsWith(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection statusEndsWith(String... value) {
        addEndsWith(MessageColumns.STATUS, value);
        return this;
    }

    public MessageSelection orderByStatus(boolean desc) {
        orderBy(MessageColumns.STATUS, desc);
        return this;
    }

    public MessageSelection orderByStatus() {
        orderBy(MessageColumns.STATUS, false);
        return this;
    }
}
