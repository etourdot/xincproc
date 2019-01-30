/**
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
/**
 * Functions for toggling chevrons on menus, so they indicate if the menu is deployed or not.
 *
 * This is purely for visual purposes. While chevrons help to indicate a menu option can be opened, animating them
 * just looks good.
 *
 * Currently only dropdown menus are supported, and they should be marked with the .chevron_toggleable class. Also
 * they should use the Font Awesome chevrons, and Bootstrap CSS classes.
 *
 * The way the chevrons work is simple: the one pointing up indicates the menu is closed, while the one pointing down
 * indicates it is open. All menus should have the closed chevron by default on the html file.
 *
 * To initialize the chevron toggle use the initChevronToggle() function.
 */

/**
 * Toggles the chevron on the active dropdown menu, so it points down, indicating it is open.
 */
function toggleDropdownChevronOpen() {
    $(this).find('.chevron_toggleable')
        .removeClass("fa fa-chevron-up")
        .addClass("fa fa-chevron-down");
}

/**
 * Toggles the chevron on the active dropdown menu, so it points up, indicating it is closed.
 */
function toggleDropdownChevronClosed() {
    $(this).find('.chevron_toggleable')
        .removeClass("fa fa-chevron-down")
        .addClass("fa fa-chevron-up");
}

/**
 * Initialize the chevron toggle feature.
 */
function initChevronToggle() {
    $('.dropdown').on('show.bs.dropdown', toggleDropdownChevronOpen);
    $('.dropdown').on('hide.bs.dropdown', toggleDropdownChevronClosed);
}
/**
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
/**
 * Functions for numbering headings.
 *
 * Only headings of level 2 and 3 will be numbered. Said numbering works in a consecutive way, like this:
 *
 * Level 1 heading
 * 1 Level 2 heading
 * 1.1 Level 3 heading
 * 2 Level 2 heading
 * 2.1 Level 3 heading
 * 2.2 Level 3 heading
 *
 * JQuery is used for this, and so is a required dependency.
 *
 * Also, the headings order is expected to be respected, and no heading level should be skipped, for example by
 * adding a level 3 heading after a level 1 heading.
 */

/**
 * Gives a number to the headings of level 2 and 3.
 *
 * This will be done in a consecutive way, and showing the heading depth, like this:
 *
 * Level 1 heading
 * 1 Level 2 heading
 * 1.1 Level 3 heading
 * 2 Level 2 heading
 * 2.1 Level 3 heading
 * 2.2 Level 3 heading
 */
function numberHeadings() {
    var indices = [];
    var firstHeading = 2;

    jQuery('h2,h3').each(function () {
        var hIndex;

        // Prepares the index for this heading
        hIndex = parseInt(this.nodeName.substring(1)) - firstHeading;

        // Initializes heading index
        if (indices.length <= hIndex) {
            // There are gaps in the numbering array
            for (var i = indices.length; i <= hIndex; i++) {
                indices[i] = 0;
            }
        } else if (indices.length > (hIndex + 1)) {
            // Lower indices are removed
            indices.splice(hIndex + 1, indices.length);
        }

        // Increases the count for the current heading
        indices[hIndex]++;

        // Displays the heading numbering
        var numbering = indices.join(".") + ". ";
        jQuery(this).prepend(numbering);
    });

}/**
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
/**
 * Initializes highlight.js.
 * 
 * This file can be overriden to change the initialization.
 */

/**
 * Initializes highlight.js when the document loads.
 */
$(document).ready(function() {

   // Uses the default initialization
   hljs.initHighlightingOnLoad();

});
/**
 * Licensed under the MIT License (http://www.opensource.org/licenses/mit-license.php)
 */
/**
 * Functions for smooth scrolling.
 *
 * This makes internal links to the same page scroll smoothly to the elements they are anchored to.
 *
 * To initialize the chevron toggle use the initSmoothScroll() function.
 */

/**
 * Scrolls smoothly to the element with the specified hash.
 *
 * @param hash hash for the element where to scroll
 */
function smoothScroll(hash) {
    var offset = $(hash).offset();
    if (offset) {
        $('html, body').animate({
            scrollTop: $(hash).offset().top
        }, 300, function () {
            // After ending, the hash is added to the URL
            window.location.hash = hash;
        });
    }
}

/**
 * Initialize the smooth scroll feature.
 */
function initSmoothScroll() {
    $('a[href^="#"]').on('click', function (e) {
        // Prevents default anchor click behavior
        e.preventDefault();

        smoothScroll(this.hash);
    });
}
