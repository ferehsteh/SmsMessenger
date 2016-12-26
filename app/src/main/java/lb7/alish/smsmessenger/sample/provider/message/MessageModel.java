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

import android.support.annotation.Nullable;

import lb7.alish.smsmessenger.sample.provider.base.BaseModel;

/**
 * Data model for the {@code message} table.
 */
@SuppressWarnings({"WeakerAccess", "unused"})
public interface MessageModel extends BaseModel {

    /**
     * Primary key.
     */
    long getId();

    /**
     * number of contact
     * Can be {@code null}.
     */
    @Nullable
    String getNumber();

    /**
     * body of Message
     * Can be {@code null}.
     */
    @Nullable
    String getMessageText();

    /**
     * Get the {@code date} value.
     * Can be {@code null}.
     */
    @Nullable
    String getDate();

    /**
     * Get the {@code thread_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getThreadId();

    /**
     * Get the {@code sms_id} value.
     * Can be {@code null}.
     */
    @Nullable
    String getSmsId();

    /**
     * Get the {@code type} value.
     * Can be {@code null}.
     */
    @Nullable
    DirectionType getType();

    /**
     * Get the {@code status} value.
     * Can be {@code null}.
     */
    @Nullable
    String getStatus();
}
