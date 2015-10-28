/**
 * FPT University Capstone Project 2015.
 * Program: HSTS.
 * Program manager: Kieu Trong Khanh.
 * Author: dangquantran.
 * Date: 10/28/2015.
 * Summary: The extension from Bootstrap Validator 0.9.0.
 */

/**
 * Fix error on multiple tabs.
 * @see https://github.com/1000hz/bootstrap-validator/issues/156
 */
$.fn.validator.Constructor.INPUT_SELECTOR = ':input:not([type="submit"], button):enabled';